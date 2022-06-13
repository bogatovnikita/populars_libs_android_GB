package com.bogatovnikita.popularslibsandroidgb

import android.app.Application
import android.content.Context
import com.bogatovnikita.popularslibsandroidgb.data.FakeUserEntityImplementation
import com.bogatovnikita.popularslibsandroidgb.domain.UserEntityRepository

class App : Application() {
    val userEntityRepositorySingleton: UserEntityRepository by lazy { FakeUserEntityImplementation() }
}

val Context.app: App get() = applicationContext as App
