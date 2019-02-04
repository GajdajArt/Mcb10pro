package com.labralab.mkbpro10.model.store

import com.labralab.mkbpro10.model.entity.Account

interface AccountStore {

    fun storeAccount(account: Account)

    fun getAccount(): Account?

    fun clearAccount()
}