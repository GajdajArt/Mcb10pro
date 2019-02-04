package com.labralab.mkbpro10.implementation.repositiry


import com.labralab.mkbpro10.model.entity.User
import com.labralab.mkbpro10.model.repository.LocalUserStore
import com.labralab.mkbpro10.model.store.PreferenceManager
import javax.inject.Inject

class LocalUserStoreImpl
@Inject constructor(private val preferenceManager: PreferenceManager): LocalUserStore {

    override fun saveUser(user: User) {
        preferenceManager[USER_ID] = user.uid
        preferenceManager[USER_NAME] = user.firstName
        preferenceManager[USER_SURNAME] = user.secondName
        preferenceManager[USER_SCORE] = user.point
    }

    override fun getUser(): User? {
        return if (preferenceManager.contains(USER_ID)) {
            val user = User(preferenceManager.getString(USER_ID))

            user.firstName = preferenceManager.getString(USER_NAME)
            user.secondName = preferenceManager.getString(USER_SURNAME)
            user.point = preferenceManager.getString(USER_SCORE)

            user
        } else {
            null
        }
    }

    companion object {

        private const val  USER_NAME = "USER_NAME"
        private const val  USER_SURNAME = "USER_SURNAME"
        private const val  USER_SCORE = "USER_SCORE"
        private const val  USER_ID = "USER_ID"
    }
}