package com.bogatovnikita.popularslibsandroidgb.di

import android.os.Handler
import android.os.Looper
import com.bogatovnikita.popularslibsandroidgb.data.retrofit.GithubApi
import com.bogatovnikita.popularslibsandroidgb.data.retrofit.RetrofitUserEntityRepositoryImplementation
import com.bogatovnikita.popularslibsandroidgb.domain.UserEntityRepository
import com.bogatovnikita.popularslibsandroidgb.ui.list_users.ListUsersViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

val appModule = module {

//    single<UserEntityRepository> {
//        FakeUserEntityImplementation(
//            get()
//        )
//    }

    single<UserEntityRepository> {
        RetrofitUserEntityRepositoryImplementation(
            get()
        )
    }

    single<Retrofit> {
        Retrofit.Builder()
            .baseUrl("https://api.github.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build()
    }

    single<GithubApi> { get<Retrofit>().create(GithubApi::class.java) }

    single { Handler(Looper.getMainLooper()) }

    viewModel { ListUsersViewModel(get()) }

}