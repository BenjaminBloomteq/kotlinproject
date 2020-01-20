package com.antcolony.social.di.Component

import android.app.Application
import com.antcolony.social.AppController
import com.antcolony.social.di.ActivityModule
import com.antcolony.social.di.DbModule
import com.antcolony.social.di.FragmentModule
import com.antcolony.social.di.ViewModelModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton


@Singleton
@Component(
    modules = [DbModule::class,
        ViewModelModule::class, AndroidSupportInjectionModule::class,
        ActivityModule::class, FragmentModule::class]
)
interface ApiComponent {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        @BindsInstance
        fun dbModule(dbModule: DbModule): Builder

        fun build(): ApiComponent

    }

    fun inject(appController: AppController)
}
