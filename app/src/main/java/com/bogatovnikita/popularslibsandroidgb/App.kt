package com.bogatovnikita.popularslibsandroidgb

import android.app.Application
import android.content.Context
import androidx.fragment.app.Fragment
import com.bogatovnikita.popularslibsandroidgb.data.FakeUserEntityImplementation
import com.bogatovnikita.popularslibsandroidgb.data.retrofit.RetrofitUserEntityRepositoryImplementation
import com.bogatovnikita.popularslibsandroidgb.domain.UserEntityRepository

class App : Application() {
    val userEntityRepositorySingleton: UserEntityRepository by lazy { FakeUserEntityImplementation() }
    val userEntityRepositorySingletonRetrofit: UserEntityRepository by lazy { RetrofitUserEntityRepositoryImplementation() }

}

val Context.app: App get() = applicationContext as App
val Fragment.app: App get() = requireContext().applicationContext as App
