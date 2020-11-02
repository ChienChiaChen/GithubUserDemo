package com.jasonchienfromtw.githubclientdemo.presentation.di.main

import androidx.appcompat.app.AppCompatActivity
import com.jasonchienfromtw.githubclientdemo.presentation.ui.main.MainActivity
import dagger.Module
import dagger.Provides

@Module(includes = [MainActivityModule.Binding::class])
class MainActivityModule(private val activity: MainActivity) {

    @Provides
    internal fun provideSupportActivity(): AppCompatActivity = activity

    @Module
    interface Binding

}