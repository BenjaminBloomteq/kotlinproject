package com.example.jetpackexampleapp.di.Component

import android.app.Application
import com.example.jetpackexampleapp.AppController
import com.example.jetpackexampleapp.di.ActivityModule
import com.example.jetpackexampleapp.di.DbModule
import com.example.jetpackexampleapp.di.FragmentModule
import com.example.jetpackexampleapp.di.ViewModelModule
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
