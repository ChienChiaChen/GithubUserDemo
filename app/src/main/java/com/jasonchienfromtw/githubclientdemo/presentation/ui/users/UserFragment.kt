package com.jasonchienfromtw.githubclientdemo.presentation.ui.users

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.jasonchienfromtw.githubclientdemo.R
import com.jasonchienfromtw.githubclientdemo.presentation.extensions.lazyFast
import com.jasonchienfromtw.githubclientdemo.presentation.extensions.viewModelProvider
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_user.*
import javax.inject.Inject

class UserFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    @Inject
    lateinit var userAdapter: UsersAdapter

    private val usersViewModel by lazyFast { viewModelProvider<UsersFragmentViewModel>(viewModelFactory) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_user, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setRecyclerView()
        usersViewModel.getUsers()
        observeUsers()
    }

    private fun setRecyclerView() {
        usersRecyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = userAdapter
        }
    }

    private fun observeUsers() {
        usersViewModel.users.observe(this, Observer { users ->
            userAdapter.submitList(users)
        })
    }

    companion object {
        const val TAG = "UserFragment"

        @JvmStatic
        fun newInstance() = UserFragment()
    }
}