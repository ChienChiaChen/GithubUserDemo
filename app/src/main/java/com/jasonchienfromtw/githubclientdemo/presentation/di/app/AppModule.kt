package com.jasonchienfromtw.githubclientdemo.presentation.di.app

import android.app.Application
import android.content.Context
import android.content.res.Resources
import com.jasonchienfromtw.githubclientdemo.App
import dagger.Module
import dagger.Provides

@Module
class AppModule(private val app: App) {

    @Provides
    internal fun provideContext(): Context = app

    @Provides
    internal fun provideResources(): Resources = app.resources

    @Provides
    internal fun provideApplication(): Application = app
}