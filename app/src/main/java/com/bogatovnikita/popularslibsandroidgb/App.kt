package com.bogatovnikita.popularslibsandroidgb

import android.app.Application
import android.content.Context

class App : Application() {
    val userEntityRepositorySingleton: UserEntityRepository by lazy { FakeUserEntityImplementation() }
}

val Context.app: App get() = applicationContext as App
