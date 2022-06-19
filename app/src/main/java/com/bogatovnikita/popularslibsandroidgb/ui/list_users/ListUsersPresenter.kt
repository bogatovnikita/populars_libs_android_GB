package com.bogatovnikita.popularslibsandroidgb.ui.list_users

import com.bogatovnikita.popularslibsandroidgb.domain.UserEntityRepository

class ListUsersPresenter(private val userEntityRepository: UserEntityRepository) :
    ListUsersContract.Presenter {
    private var view: ListUsersContract.View? = null


    override fun attach(view: ListUsersContract.View) {
        this.view = view
    }

    override fun detach() {
        view = null
    }

    override fun onRefresh() {
        loadData()
    }

    private fun loadData() {
        view?.showProgress(true)
        userEntityRepository.getUsers(onSuccess = {
            view?.showProgress(false)
            view?.showUsers(it)
        }, onError = {
            view?.showProgress(false)
            view?.showError(it)
        })
    }

}