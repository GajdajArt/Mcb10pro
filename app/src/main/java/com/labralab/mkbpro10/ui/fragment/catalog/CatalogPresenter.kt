package com.labralab.mkbpro10.ui.fragment.catalog

import android.annotation.SuppressLint
import com.labralab.mkbpro10.di.Schedulers
import com.labralab.mkbpro10.exception.NoSubCatalogException
import com.labralab.mkbpro10.model.entity.Section
import com.labralab.mkbpro10.model.repository.LocalUserStore
import com.labralab.mkbpro10.model.store.MKB10Store
import com.labralab.mkbpro10.ui.base.BasePresenter
import io.reactivex.Scheduler
import javax.inject.Inject
import javax.inject.Named

class CatalogPresenter
@Inject constructor(private val catalogRouter: CatalogRouter,
                    private val localUserStore: LocalUserStore,
                    private val mkB10Store: MKB10Store,
                    @Named(Schedulers.IO_SCHEDULER) private val ioScheduler: Scheduler,
                    @Named(Schedulers.UI_SCHEDULER) private val uiScheduler: Scheduler):
        BasePresenter<CatalogContract.View>(),
        CatalogContract.Presenter {

    private lateinit var parent: String

    private var list: List<Section>? = null
    private var section: Section? = null

    override fun setParent(parent: String) {
        this.parent = parent
    }

    override fun onBind() {
        super.onBind()

        if (list == null) {
            getSections()
        } else {
            view?.showUserData(localUserStore.getUser())
            view?.showList(list!!)
        }

        view?.stopRefreshing()
    }

    @SuppressLint("CheckResult")
    private fun getSections() {
        view?.showProgressDialog()

        subscribe(1, mkB10Store.getMKBList(parent)
            .subscribeOn(ioScheduler)
            .observeOn(uiScheduler)
            .subscribe({
                list = it
                view?.showUserData(localUserStore.getUser())
                view?.showList(it)
                view?.hideProgressDialog()
            },{onError(it)}))
    }

    private fun onError(throwable: Throwable) {
        if (throwable is NoSubCatalogException) {
            showDetails()
        } else {
            view?.hideProgressDialog()
            view?.showMessage(throwable.message.toString())
        }
    }

    private fun showDetails() {
        subscribe(2, mkB10Store.getDetails(parent)
            .subscribeOn(ioScheduler)
            .observeOn(uiScheduler)
            .subscribe({
                section = it
                view?.showUserData(localUserStore.getUser())
                view?.showLastSection(section!!)
                view?.hideProgressDialog()
            },{onError(it)}))
    }

    override fun onItemClick(section: Section) {
        catalogRouter.openNextSection(section.id)
    }

    override fun onAccountHeaderClick() {
        catalogRouter.openAccount()
    }
}