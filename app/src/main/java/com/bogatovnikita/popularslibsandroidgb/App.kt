package com.bogatovnikita.popularslibsandroidgb

import android.app.Application
import android.content.Context
import androidx.fragment.app.Fragment
import com.bogatovnikita.popularslibsandroidgb.di.AppComponent
import com.bogatovnikita.popularslibsandroidgb.di.DaggerAppComponent

class App : Application() {
    val appComponent: AppComponent by lazy { DaggerAppComponent.create() }
}

val Context.app: App get() = applicationContext as App
val Fragment.app: App get() = requireContext().applicationContext as App
