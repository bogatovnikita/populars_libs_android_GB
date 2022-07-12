package com.bogatovnikita.popularslibsandroidgb.di

import com.bogatovnikita.popularslibsandroidgb.ui.list_users.ListUsersFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {
    fun inject(listUsersFragment: ListUsersFragment)
}