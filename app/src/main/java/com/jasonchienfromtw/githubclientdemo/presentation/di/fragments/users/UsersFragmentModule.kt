package com.jasonchienfromtw.githubclientdemo.presentation.di.fragments.users

import com.jasonchienfromtw.githubclientdemo.presentation.ui.users.UserFragment
import dagger.Module


@Module(includes = [UsersFragmentModule.Binding::class])
class UsersFragmentModule(private val fragment: UserFragment) {

    @Module
    interface Binding
}