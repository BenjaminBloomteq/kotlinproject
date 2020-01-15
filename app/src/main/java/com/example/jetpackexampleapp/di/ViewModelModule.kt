package com.example.jetpackexampleapp.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.jetpackexampleapp.ui.feed.FeedViewModel
import com.example.jetpackexampleapp.ui.feed.PostViewModel
import com.example.jetpackexampleapp.ui.register.RegisterViewModel
import com.example.jetpackexampleapp.ui.signin.SignInViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
internal abstract class ViewModelModule {

    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(RegisterViewModel::class)
    protected abstract fun registerViewModel(registerViewModel: RegisterViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(SignInViewModel::class)
    protected abstract fun signInViewModel(signInViewModel: SignInViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(PostViewModel::class)
    protected abstract fun postViewModel(postViewModel: PostViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(FeedViewModel::class)
    protected abstract fun feedViewModel(feedViewModel: FeedViewModel): ViewModel
}