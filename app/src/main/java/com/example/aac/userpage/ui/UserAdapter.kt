package com.example.aac.userpage.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.aac.R
import com.example.aac.databinding.UserListItemBinding
import com.example.aac.userpage.data.model.User

class UserAdapter : ListAdapter<User, UserAdapter.UserViewHolder>(UserComparator()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val binding = DataBindingUtil.inflate<UserListItemBinding>(LayoutInflater.from(parent.context),
            R.layout.user_list_item,parent,false)
        return UserViewHolder.create(binding)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val current = getItem(position)
        holder.bind(current)
    }

    class UserViewHolder(private val binding: UserListItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(user: User?) {
            binding.user = user
        }

        companion object {
            fun create(binding: UserListItemBinding): UserViewHolder {
                return UserViewHolder(binding)
            }
        }
    }

    class UserComparator : DiffUtil.ItemCallback<User>() {
        override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
            return oldItem.id == newItem.id
        }
    }
}