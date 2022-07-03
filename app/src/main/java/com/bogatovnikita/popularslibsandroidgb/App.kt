package com.bogatovnikita.popularslibsandroidgb

import android.app.Application
import android.content.Context
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import com.bogatovnikita.popularslibsandroidgb.data.FakeUserEntityImplementation
import com.bogatovnikita.popularslibsandroidgb.data.retrofit.GithubApi
import com.bogatovnikita.popularslibsandroidgb.data.retrofit.RetrofitUserEntityRepositoryImplementation
import com.bogatovnikita.popularslibsandroidgb.domain.UserEntityRepository
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class App : Application() {
    val userEntityRepositorySingleton: UserEntityRepository by lazy {
        FakeUserEntityImplementation(
            handler = handler
        )
    }

    val userEntityRepositorySingletonRetrofit: UserEntityRepository by lazy {
        RetrofitUserEntityRepositoryImplementation(
            api = api
        )
    }

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl("https://api.github.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build()
    }

    private val api by lazy { retrofit.create(GithubApi::class.java) }

    private val handler by lazy { Handler(Looper.getMainLooper()) }
}

val Context.app: App get() = applicationContext as App
val Fragment.app: App get() = requireContext().applicationContext as App
