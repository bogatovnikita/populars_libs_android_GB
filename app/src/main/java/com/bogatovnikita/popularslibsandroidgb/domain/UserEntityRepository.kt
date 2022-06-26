package com.bogatovnikita.popularslibsandroidgb.domain

import io.reactivex.rxjava3.core.Single

interface UserEntityRepository {
    fun getUsers(
        onSuccess: (List<UserEntity>) -> Unit,
        onError: ((Throwable) -> Unit)? = null
    )

    fun getUsers(): Single<List<UserEntity>>
}