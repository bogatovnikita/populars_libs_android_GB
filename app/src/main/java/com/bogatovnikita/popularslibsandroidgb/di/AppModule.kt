package com.bogatovnikita.popularslibsandroidgb.di

import com.bogatovnikita.popularslibsandroidgb.data.retrofit.GithubApi
import com.bogatovnikita.popularslibsandroidgb.data.retrofit.RetrofitUserEntityRepositoryImplementation
import com.bogatovnikita.popularslibsandroidgb.domain.UserEntityRepository
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class AppModule {

    @Provides
    @Singleton
    fun provideUserEntityRepository(api: GithubApi): UserEntityRepository {
        return RetrofitUserEntityRepositoryImplementation(api = api)
    }

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://api.github.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideGitHubApi(retrofit: Retrofit): GithubApi {
        return retrofit.create(GithubApi::class.java)
    }
}