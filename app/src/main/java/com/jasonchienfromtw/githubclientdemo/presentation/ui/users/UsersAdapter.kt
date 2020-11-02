package com.jasonchienfromtw.githubclientdemo.presentation.ui.users

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.jasonchienfromtw.githubclientdemo.R
import com.jasonchienfromtw.githubclientdemo.domain.models.User
import com.jasonchienfromtw.githubclientdemo.presentation.extensions.setImageUrl
import com.jasonchienfromtw.githubclientdemo.presentation.ui.navigator.Navigator
import kotlinx.android.synthetic.main.item_user.view.*
import javax.inject.Inject

class UsersAdapter @Inject constructor(private val navigator: Navigator) :
    PagedListAdapter<User, UsersAdapter.UserViewHolder>(userDiffCallback) {

    private val onClickListener: ((
        itemView: View,
        adapterPosition: Int,
        user: User
    ) -> Unit) = { itemView, adapterPosition, user ->
        itemView.userAvatar.transitionName = itemView.context.getString(R.string.user_image_transition, adapterPosition)
        navigator.toDetailFragment(user, adapterPosition, itemView.userAvatar)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return UserViewHolder(inflater.inflate(R.layout.item_user, parent, false))
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val user = getItem(position)
        user?.let { holder.bind(user, onClickListener) }
    }

    class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(
            user: User,
            onClickListener: ((itemView: View, adapterPosition: Int, user: User) -> Unit)
        ) {
            itemView.userName.text = user.name
            itemView.userAvatar.setImageUrl(user.avatarUrl)
            itemView.setOnClickListener { onClickListener.invoke(itemView, adapterPosition, user) }
        }
    }

    companion object {
        private val userDiffCallback = object : DiffUtil.ItemCallback<User>() {
            override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
                return oldItem.id == newItem.id && oldItem.name == newItem.name
            }

            override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
                return oldItem == newItem
            }
        }
    }

}