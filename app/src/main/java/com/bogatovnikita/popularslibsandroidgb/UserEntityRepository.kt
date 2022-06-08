package com.bogatovnikita.popularslibsandroidgb

interface UserEntityRepository {
    fun getUsers(
        onSuccess: (List<UserEntity>) -> Unit,
        onError: ((Throwable) -> Unit)? = null
    )
}