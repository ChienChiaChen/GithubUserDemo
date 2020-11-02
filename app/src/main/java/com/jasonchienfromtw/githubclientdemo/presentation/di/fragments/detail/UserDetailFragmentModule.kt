package com.jasonchienfromtw.githubclientdemo.presentation.di.fragments.detail

import com.jasonchienfromtw.githubclientdemo.presentation.ui.userdetail.UserDetailFragment
import dagger.Module

@Module(includes = [UserDetailFragmentModule.Binding::class])
class UserDetailFragmentModule(private val fragment: UserDetailFragment) {

    @Module
    interface Binding {
    }
}