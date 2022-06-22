package com.bogatovnikita.popularslibsandroidgb.data.retrofit

import com.bogatovnikita.popularslibsandroidgb.domain.UserEntity
import retrofit2.Call
import retrofit2.http.GET

interface GithubApi {

    @GET("users")
    fun getUsers(): Call<List<UserEntity>>
}