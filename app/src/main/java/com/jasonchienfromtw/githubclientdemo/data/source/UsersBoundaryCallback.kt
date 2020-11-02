package com.jasonchienfromtw.githubclientdemo.data.source

import android.util.Log
import androidx.paging.PagedList
import com.jasonchienfromtw.githubclientdemo.data.mapper.mapDomainUserToLocal
import com.jasonchienfromtw.githubclientdemo.data.source.local.UsersLocalSource
import com.jasonchienfromtw.githubclientdemo.data.source.remote.UsersRemoteSource
import com.jasonchienfromtw.githubclientdemo.domain.models.User
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class UsersBoundaryCallback(
    private val usersLocalSource: UsersLocalSource,
    private val usersRemoteSource: UsersRemoteSource
) : PagedList.BoundaryCallback<User>() {

    private var nextPage = 1
    private var isRequestRunning = false
    var disposable: Disposable? = null

    override fun onZeroItemsLoaded() {
        getUsersFromApiAndSaveInDatabase()
    }

    override fun onItemAtEndLoaded(itemAtEnd: User) {
        getUsersFromApiAndSaveInDatabase()
    }

    private fun getUsersFromApiAndSaveInDatabase() {
        if (isRequestRunning) return

        isRequestRunning = true

        disposable = usersRemoteSource.getUsersFromApi(nextPage)
            .map { users -> users.map(mapDomainUserToLocal) }
            .doOnSuccess { users ->
                usersLocalSource.saveUsersInDatabase(users)
                nextPage++
            }
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.io())
            .ignoreElement()
            .doFinally { isRequestRunning = false }
            .subscribe({
                Log.e("Jason ", "$nextPage page retrieved from API and users saved in database")
            },
                { it.printStackTrace() }
            )
    }
}