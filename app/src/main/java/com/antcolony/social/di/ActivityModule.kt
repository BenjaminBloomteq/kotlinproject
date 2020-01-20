package com.antcolony.social.di

import com.antcolony.social.ui.MainActivity
import com.antcolony.social.ui.feed.FeedActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module
abstract class ActivityModule {

    @ContributesAndroidInjector(modules = [FragmentModule::class])
    abstract fun contributeMainActivity(): MainActivity

    @ContributesAndroidInjector(modules = [FragmentModule::class])
    abstract fun contributeFeedActivity(): FeedActivity
}