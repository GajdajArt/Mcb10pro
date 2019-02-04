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


class RemoteUserRepository
@Inject constructor(private val database: FirebaseDatabase,
                    private val localUserStore: LocalUserStore): UserRepository {

    private val databaseReference = database.getReference(USER_NODE)

    override fun createUser(user: User) {
//        databaseReference.child("${user.uid}").setValue(user)
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