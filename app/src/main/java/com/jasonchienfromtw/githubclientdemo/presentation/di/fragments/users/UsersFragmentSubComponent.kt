package com.jasonchienfromtw.githubclientdemo.presentation.di.fragments.users

import com.jasonchienfromtw.githubclientdemo.presentation.di.scope.PerFragment
import com.jasonchienfromtw.githubclientdemo.presentation.ui.users.UserFragment
import dagger.Subcomponent
import dagger.android.AndroidInjector

@Subcomponent(modules = [UsersFragmentModule::class])
@PerFragment
interface UsersFragmentSubComponent : AndroidInjector<UserFragment> {

    @Subcomponent.Builder
    abstract class Builder : AndroidInjector.Builder<UserFragment>() {

        abstract fun module(module: UsersFragmentModule): Builder

        override fun seedInstance(instance: UserFragment) {
            module(UsersFragmentModule(instance))
        }
    }

}