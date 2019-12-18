package com.example.jetpackexampleapp.di

import com.example.jetpackexampleapp.ui.list.PeoplesListFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentModule {

    @ContributesAndroidInjector
    abstract fun peoplesListFragment(): PeoplesListFragment
}