package com.bogatovnikita.popularslibsandroidgb.ui.list_users

import com.bogatovnikita.popularslibsandroidgb.domain.UserEntity
import io.reactivex.rxjava3.core.Observable

interface ListUsersContract {

    interface ViewModel {
        val userObservable: Observable<List<UserEntity>>
        val errorObservable: Observable<Throwable>
        val progressObservable: Observable<Boolean>

        fun onRefresh()
    }

}