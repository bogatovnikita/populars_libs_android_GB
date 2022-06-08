package com.bogatovnikita.popularslibsandroidgb

interface UserEntityRepository {
    fun getUsers(
        onSuccess: (MutableList<UserEntity>) -> Unit,
        onError: ((Throwable) -> Unit)? = null
    )
}