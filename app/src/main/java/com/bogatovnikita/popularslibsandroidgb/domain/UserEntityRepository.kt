package com.bogatovnikita.popularslibsandroidgb.domain

interface UserEntityRepository {
    fun getUsers(
        onSuccess: (MutableList<UserEntity>) -> Unit,
        onError: ((Throwable) -> Unit)? = null
    )
}