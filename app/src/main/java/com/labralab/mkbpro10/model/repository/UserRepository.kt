package com.labralab.mkbpro10.model.repository

import com.labralab.mkbpro10.model.entity.User
import io.reactivex.Single

interface UserRepository {

    fun startUserDataChangeListener(userId: String)

    fun createUser(user: User)
}