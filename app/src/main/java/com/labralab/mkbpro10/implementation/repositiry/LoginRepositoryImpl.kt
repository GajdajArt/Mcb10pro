package com.labralab.mkbpro10.implementation.repositiry

import com.labralab.mkbpro10.model.entity.Account
import com.labralab.mkbpro10.model.entity.User
import com.labralab.mkbpro10.model.repository.LoginRepository
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import io.reactivex.Completable
import javax.inject.Inject

class LoginRepositoryImpl
@Inject
constructor(private val firebaseAuth: FirebaseAuth) : LoginRepository {

    private lateinit var callBack: LoginRepository.LoginCallBack
    private lateinit var errorCallBack: LoginRepository.LoginErrorCallBack

    override fun sineOut(): Completable {
        return Completable.fromCallable {
            firebaseAuth.signOut()
        }
    }

    override fun loginAccount(account: Account, callBack: LoginRepository.LoginCallBack,
                              loginErrorCallBack: LoginRepository.LoginErrorCallBack): Completable {
        this.callBack = callBack
        this.errorCallBack = loginErrorCallBack
        return Completable.fromCallable {
            try {
                signIn(account)
            } catch (e: Throwable) {
                Completable.error(e)
            }
        }
    }

    private fun signIn(account: Account) {
        firebaseAuth.signInWithEmailAndPassword(account.login, account.password)
                .addOnCompleteListener {

                    if (it.isSuccessful) {
                        callBack.onLogin(User(firebaseAuth.currentUser?.uid!!))
                    } else {
                        errorCallBack.onError(Throwable("Wrong Login or Password"))
                    }
                }
    }
}


