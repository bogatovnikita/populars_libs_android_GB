package com.bogatovnikita.popularslibsandroidgb.ui.list_users

import android.util.Log
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bogatovnikita.popularslibsandroidgb.domain.UserEntity

class UserEntityAdapter(private val clickListener: (UserEntity) -> Unit) :
    RecyclerView.Adapter<UserEntityViewHolder>() {

    private var usersList = mutableListOf<UserEntity>()

    fun setListUsers(usersItemList: MutableList<UserEntity>) {
        usersList.clear()
        usersList.addAll(usersItemList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserEntityViewHolder =
        UserEntityViewHolder(parent) {
            clickListener(usersList[it])
            Log.e("pie", "UserEntityAdapter:onCreateViewHolder $it")
        }

    override fun onBindViewHolder(holderViewHolder: UserEntityViewHolder, position: Int) {
        holderViewHolder.bind(usersList[position])
    }

    override fun getItemCount(): Int = usersList.size
}