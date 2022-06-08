package com.bogatovnikita.popularslibsandroidgb.ui.adapters

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bogatovnikita.popularslibsandroidgb.domain.UserEntity

class AdapterUserEntity : RecyclerView.Adapter<ViewHolderUserEntity>() {

    private var usersList = mutableListOf<UserEntity>()

    fun setListUsers(usersItemList: MutableList<UserEntity>) {
        usersList.clear()
        usersList.addAll(usersItemList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderUserEntity =
        ViewHolderUserEntity(parent)

    override fun onBindViewHolder(holder: ViewHolderUserEntity, position: Int) {
        holder.bind(usersList[position])
    }

    override fun getItemCount(): Int = usersList.size
}