package com.bogatovnikita.popularslibsandroidgb.ui.list_users

import com.bogatovnikita.popularslibsandroidgb.domain.UserEntity

class ListUsersContract {

    interface View {
        fun showUsers(users: MutableList<UserEntity>)
        fun showError(throwable: Throwable)
        fun showProgress(inProgress: Boolean)
    }

    interface Presenter {
        fun attach(view: View)
        fun detach()

        fun onRefresh()
    }

}