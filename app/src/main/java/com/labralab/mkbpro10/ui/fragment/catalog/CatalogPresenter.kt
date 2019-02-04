package com.labralab.mkbpro10.ui.fragment.catalog

import com.labralab.mkbpro10.di.Schedulers
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
    }

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
            },{
                view?.showMessage(it.message.toString())
            }))
    }

    override fun onItemClick(section: Section) {
        catalogRouter.openNextSection(section.id)
    }

    override fun onAccountHeaderClick() {
        catalogRouter.openAccount()
    }
}