package com.bogatovnikita.popularslibsandroidgb.domain

interface UserEntityRepository {
    fun getUsers(
        onSuccess: (List<UserEntity>) -> Unit,
        onError: ((Throwable) -> Unit)? = null
    )
}