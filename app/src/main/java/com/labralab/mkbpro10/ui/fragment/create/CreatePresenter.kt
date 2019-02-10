package com.labralab.mkbpro10.ui.fragment.create

import com.labralab.mkbpro10.di.Schedulers
import com.labralab.mkbpro10.model.entity.User
import com.labralab.mkbpro10.model.repository.LocalUserStore
import com.labralab.mkbpro10.model.repository.UserRepository
import com.labralab.mkbpro10.ui.base.BasePresenter
import io.reactivex.Scheduler
import javax.inject.Inject
import javax.inject.Named

class CreatePresenter
@Inject constructor(private val createRouter: CreateRouter,
                    private val localUserStore: LocalUserStore,
                    private val userRepository: UserRepository,
                    @Named(Schedulers.IO_SCHEDULER) private val ioScheduler: Scheduler,
                    @Named(Schedulers.UI_SCHEDULER) private val uiScheduler: Scheduler
) : BasePresenter<CreateContract.View>(), CreateContract.Presenter {

    override fun saveData(user: User) {
        view?.ctearErrors()
        view?.showProgressDialog()

        if (isValidUser(user)) {
           saveUser(user)
        } else {
            view?.hideProgressDialog()
            view?.showValidationErrors()
        }
    }

    private fun saveUser(user: User) {
        subscribe(1, userRepository.createUser(buildUserToSave(user))
            .subscribeOn(ioScheduler)
            .observeOn(uiScheduler)
            .subscribe({
                localUserStore.saveUser(buildUserToSave(user))
                view?.hideProgressDialog()
                createRouter.openCatalog()
            },{processError(it)}))
    }

    private fun buildUserToSave(user: User): User {
        localUserStore.getUser()?.let {
            user.uid = it.uid
            user.point = "0"
        } ?: run { processError(Throwable("User did not save to local"))  }
        return user
    }

    private fun isValidUser(user: User): Boolean {
        return !user.firstName.isNullOrBlank() && !user.secondName.isNullOrBlank()
    }
}