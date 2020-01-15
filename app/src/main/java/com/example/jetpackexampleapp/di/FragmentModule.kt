package com.example.jetpackexampleapp.di

import com.example.jetpackexampleapp.ui.feed.PostFragment
import com.example.jetpackexampleapp.ui.list.PeoplesListFragment
import com.example.jetpackexampleapp.ui.register.RegisterFragment
import com.example.jetpackexampleapp.ui.signin.SignInFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentModule {

    @ContributesAndroidInjector
    abstract fun peoplesListFragment(): PeoplesListFragment

    @ContributesAndroidInjector
    abstract fun registerFragment(): RegisterFragment

    @ContributesAndroidInjector
    abstract fun signInFragment(): SignInFragment

    @ContributesAndroidInjector
    abstract fun postFragment(): PostFragment
}