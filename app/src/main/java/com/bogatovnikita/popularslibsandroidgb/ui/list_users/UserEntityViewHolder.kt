package com.bogatovnikita.popularslibsandroidgb.ui.list_users

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.bogatovnikita.popularslibsandroidgb.R
import com.bogatovnikita.popularslibsandroidgb.databinding.ItemUserEntityBinding
import com.bogatovnikita.popularslibsandroidgb.domain.UserEntity

class UserEntityViewHolder(parent: ViewGroup, private val clickAtPosition: (Int) -> Unit) :
    RecyclerView.ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_user_entity, parent, false)
    ) {
    private val binding = ItemUserEntityBinding.bind(itemView)

    init {
        itemView.setOnClickListener {
            clickAtPosition(adapterPosition)
            Log.e("pie", "UserEntityViewHolder:init $it")
        }
    }

    fun bind(userEntity: UserEntity) {
        with(binding) {
            iconUserImageView.load(userEntity.avatar)
            idUserTextView.text = userEntity.id.toString()
            nameUserTextView.text = userEntity.login
        }
    }
}