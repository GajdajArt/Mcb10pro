package com.labralab.mkbpro10.implementation.repositiry

import com.google.firebase.database.FirebaseDatabase
import com.labralab.mkbpro10.model.entity.User
import com.labralab.mkbpro10.model.repository.UserRepository
import javax.inject.Inject
import com.google.firebase.database.DatabaseError
import android.support.constraint.Constraints.TAG
import android.util.Log
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.ValueEventListener
import com.labralab.mkbpro10.model.repository.LocalUserStore
import io.reactivex.Completable
import io.reactivex.Single


class RemoteUserRepository
@Inject constructor(
    private val database: FirebaseDatabase,
    private val localUserStore: LocalUserStore
) : UserRepository {

    private val databaseReference = database.getReference(USER_NODE)

    override fun isUserCreated(user: User): Single<Boolean> {
        return Single.create {
            databaseReference.addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onCancelled(data: DatabaseError) {
                    it.onError(Throwable(data.message))
                }

                override fun onDataChange(data: DataSnapshot) {
                    it.onSuccess(data.hasChild(user.uid))
                }
            })
        }
    }

    override fun createUser(user: User): Completable {
        return Completable.create {
            val emitter = it
            databaseReference.child(user.uid).setValue(user)
                .addOnCanceledListener {
                    emitter.onError(Throwable())
                }
                .addOnCompleteListener {
                    emitter.onComplete()
                }
        }
    }

    override fun startUserDataChangeListener(userId: String) {
        databaseReference.child(userId).addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val user = dataSnapshot.getValue(User::class.java)

                if (user == null) {
                    Log.e(TAG, "User data is null!")
                    return
                }

                localUserStore.saveUser(user)
            }

            override fun onCancelled(error: DatabaseError) {
                Log.e(TAG, "Failed to read user", error.toException())
            }
        })
    }

    companion object {
        private const val USER_NODE = "USERS"
    }
}