package com.bogatovnikita.popularslibsandroidgb.data.retrofit

import com.bogatovnikita.popularslibsandroidgb.domain.UserEntity
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET

interface GithubApi {

    @GET("users")
    fun getUsers(): Single<List<UserEntity>>
}