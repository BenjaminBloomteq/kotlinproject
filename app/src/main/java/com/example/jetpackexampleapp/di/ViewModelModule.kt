package com.example.jetpackexampleapp.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.jetpackexampleapp.ui.list.PeoplesListViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
internal abstract class ViewModelModule {

    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory


    @Binds
    @IntoMap
    @ViewModelKey(PeoplesListViewModel::class)
    protected abstract fun peoplesListViewModel(moviesListViewModel: PeoplesListViewModel): ViewModel
}