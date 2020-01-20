package com.antcolony.social.di

import com.antcolony.social.ui.feed.PostFragment
import com.antcolony.social.ui.register.RegisterFragment
import com.antcolony.social.ui.signin.SignInFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentModule {

    @ContributesAndroidInjector
    abstract fun registerFragment(): RegisterFragment

    @ContributesAndroidInjector
    abstract fun signInFragment(): SignInFragment

    @ContributesAndroidInjector
    abstract fun postFragment(): PostFragment
}