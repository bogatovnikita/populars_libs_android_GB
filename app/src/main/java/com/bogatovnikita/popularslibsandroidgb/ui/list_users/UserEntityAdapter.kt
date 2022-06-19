package com.bogatovnikita.popularslibsandroidgb.ui.list_users

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bogatovnikita.popularslibsandroidgb.domain.UserEntity

class UserEntityAdapter : RecyclerView.Adapter<UserEntityViewHolder>() {

    private var usersList = mutableListOf<UserEntity>()

    fun setListUsers(usersItemList: MutableList<UserEntity>) {
        usersList.clear()
        usersList.addAll(usersItemList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserEntityViewHolder =
        UserEntityViewHolder(parent)

    override fun onBindViewHolder(holderViewHolder: UserEntityViewHolder, position: Int) {
        holderViewHolder.bind(usersList[position])
    }

    override fun getItemCount(): Int = usersList.size
}