package com.example.jetpackexampleapp.di

import com.example.jetpackexampleapp.ui.MainActivity
import com.example.jetpackexampleapp.ui.feed.FeedActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module
abstract class ActivityModule {

    @ContributesAndroidInjector(modules = [FragmentModule::class])
    abstract fun contributeMainActivity(): MainActivity

    @ContributesAndroidInjector(modules = [FragmentModule::class])
    abstract fun contributeFeedActivity(): FeedActivity
}