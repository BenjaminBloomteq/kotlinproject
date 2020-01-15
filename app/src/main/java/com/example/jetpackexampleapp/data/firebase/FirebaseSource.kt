//package com.example.jetpackexampleapp.data.firebase
//
//import android.app.Application
//import android.widget.Toast
//import com.example.jetpackexampleapp.R
//import com.example.jetpackexampleapp.data.model.People
//import com.google.firebase.auth.FirebaseAuth
//import com.google.firebase.database.FirebaseDatabase
//import dagger.Module
//import dagger.Provides
//import io.reactivex.Completable
//
//@Module
//class FirebaseSource {
//
//
//    private val firebaseAuth: FirebaseAuth by lazy {
//        FirebaseAuth.getInstance()
//    }
//
//    @Provides
//    internal fun getInstance(): FirebaseSource {
//        return FirebaseSource()
//    }
//
//    fun login(email: String, password: String) = Completable.create { emitter ->
//        firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener {
//            if (!emitter.isDisposed) {
//                if (it.isSuccessful)
//                    emitter.onComplete()
//                else
//                    emitter.onError(it.exception!!)
//            }
//        }
//    }
//
//
//    fun register(email: String, password: String, name: String) =
//        Completable.create { emitter ->
//            firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener {
//                FirebaseDatabase.getInstance().getReference("Person")
//                    .child(firebaseAuth.currentUser?.uid.toString())
//                    .setValue(name).addOnCompleteListener {
//                        if (!emitter.isDisposed) {
//                            if (it.isSuccessful) {
//                                Toast.makeText(
//                                    Application(),
//                                    R.string.account_created,
//                                    Toast.LENGTH_SHORT
//                                ).show()
//                            }
//                            else {
//                                emitter.onError(it.exception!!)
//                            }
//                        }
//                    }
//            }
//        }
//
//    fun logout() = firebaseAuth.signOut()
//
//    fun currentUser() = firebaseAuth.currentUser
//}