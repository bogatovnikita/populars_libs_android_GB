package com.bogatovnikita.popularslibsandroidgb.ui.list_users

import androidx.lifecycle.LiveData
import com.bogatovnikita.popularslibsandroidgb.domain.UserEntity

interface ListUsersContract {

    interface ViewModel {
        val userLiveData: LiveData<List<UserEntity>>
        val errorLiveData: LiveData<Throwable>
        val progressLiveData: LiveData<Boolean>

        fun onRefresh()
    }

}