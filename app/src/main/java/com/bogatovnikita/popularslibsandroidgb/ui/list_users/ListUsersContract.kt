package com.bogatovnikita.popularslibsandroidgb.ui.list_users

import com.bogatovnikita.popularslibsandroidgb.domain.UserEntity
import io.reactivex.rxjava3.core.Observable

interface ListUsersContract {

    interface ViewModel {
        val userLiveData: Observable<List<UserEntity>>
        val errorLiveData: Observable<Throwable>
        val progressLiveData: Observable<Boolean>

        fun onRefresh()
    }

}