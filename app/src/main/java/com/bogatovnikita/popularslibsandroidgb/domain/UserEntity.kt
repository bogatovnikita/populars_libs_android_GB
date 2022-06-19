package com.bogatovnikita.popularslibsandroidgb.domain

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserEntity(
    val login: String,
    val id: Int,
    val avatar: String,
    val description: Int
) : Parcelable