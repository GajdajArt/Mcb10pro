package com.labralab.mkbpro10.model.repository

import com.labralab.mkbpro10.model.entity.User

interface LocalUserStore {

    fun saveUser(user: User)

    fun getUser(): User?
}