//package com.example.jetpackexampleapp.data.auth
//
//import androidx.lifecycle.ViewModel
//import com.example.jetpackexampleapp.data.firebase.FirebaseSource
//import com.example.jetpackexampleapp.data.model.People
//import com.example.jetpackexampleapp.data.repositories.PeopleRepository
//import com.raywenderlich.android.imet.data.db.PeopleDao
//import io.reactivex.android.schedulers.AndroidSchedulers
//import io.reactivex.disposables.CompositeDisposable
//import io.reactivex.schedulers.Schedulers
//import javax.inject.Inject
//
//class AuthViewModel
//@Inject constructor(peopleDao: PeopleDao, firebaseSource: FirebaseSource) : ViewModel() {
//
//    var peopleRepository = PeopleRepository(peopleDao, firebaseSource)
//    var email: String? = null
//    var password: String? = null
//    var name: String? = null
//
//    var authListener: AuthListener? = null
//
//    private val disposables = CompositeDisposable()
//
//    fun login() {
//
//        if (email.isNullOrEmpty() || password.isNullOrEmpty()) {
//            authListener?.onFailure("Invalid email or password")
//            return
//        }
//
//        authListener?.onStarted()
//
//        val disposable = peopleRepository.login(email!!, password!!)
//            .subscribeOn(Schedulers.io())
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribe({
//                authListener?.onSuccess()
//            }, {
//                authListener?.onFailure(it.message!!)
//            })
//        disposables.add(disposable)
//    }
//
//    fun signup() {
//
//        if (email.isNullOrEmpty() || password.isNullOrEmpty()) {
//            authListener?.onFailure("Please input all values")
//            return
//        }
//
//        authListener?.onStarted()
//        val disposable = peopleRepository.register(email!!, password!!, name!!)
//            .subscribeOn(Schedulers.io())
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribe({
//                authListener?.onSuccess()
//            }, {
//                authListener?.onFailure(it.message!!)
//            })
//        disposables.add(disposable)
//    }
//
//    override fun onCleared() {
//        super.onCleared()
//        disposables.dispose()
//    }
//
//
//}