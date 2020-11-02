package com.jasonchienfromtw.githubclientdemo.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.jasonchienfromtw.githubclientdemo.data.source.constants.RepositoryConstants.DATABASE_VERSION
import com.jasonchienfromtw.githubclientdemo.data.source.local.room.entity.UserEntity

@Database(entities = [UserEntity::class], version = DATABASE_VERSION)
abstract class UsersDatabase: RoomDatabase() {
    abstract fun usersDao(): UsersDao
}