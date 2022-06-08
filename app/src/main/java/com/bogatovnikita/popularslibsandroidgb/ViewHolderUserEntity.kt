package com.bogatovnikita.popularslibsandroidgb

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.bogatovnikita.popularslibsandroidgb.databinding.ItemUserEntityBinding

class ViewHolderUserEntity(parent: ViewGroup) : RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context).inflate(R.layout.item_user_entity, parent, false)
) {
    private val binding = ItemUserEntityBinding.bind(itemView)

    fun bind(userEntity: UserEntity) {
        with(binding) {
            iconItemUserEntity.load(userEntity.avatar)
            idItemUserEntity.text = userEntity.id.toString()
            nameItemUserEntity.text = userEntity.login
        }
    }
}