package com.jasonchienfromtw.githubclientdemo.presentation.di.app

import com.jasonchienfromtw.githubclientdemo.App
import com.jasonchienfromtw.githubclientdemo.presentation.di.data.LocalDataModule
import com.jasonchienfromtw.githubclientdemo.presentation.di.data.RemoteDataModule
import com.jasonchienfromtw.githubclientdemo.presentation.di.domain.DomainModule
import com.jasonchienfromtw.githubclientdemo.presentation.di.main.MainActivityInjector
import com.jasonchienfromtw.githubclientdemo.presentation.di.viewmodel.ViewModelModule
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton

@Component(
    modules = [
        AndroidInjectionModule::class,
        AppModule::class,
        MainActivityInjector::class,
        RemoteDataModule::class,
        LocalDataModule::class,
        ViewModelModule::class,
        DomainModule::class
    ]
)
@Singleton
interface AppComponent : AndroidInjector<App> {

    @Component.Builder
    abstract class Builder : AndroidInjector.Builder<App>() {

        internal abstract fun module(module: AppModule): Builder

        override fun seedInstance(instance: App) {
            module(AppModule(instance))
        }
    }

}