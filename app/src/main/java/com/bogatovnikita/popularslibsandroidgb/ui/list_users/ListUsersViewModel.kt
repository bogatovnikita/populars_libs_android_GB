package com.bogatovnikita.popularslibsandroidgb.ui.list_users

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.bogatovnikita.popularslibsandroidgb.domain.UserEntity
import com.bogatovnikita.popularslibsandroidgb.domain.UserEntityRepository
import com.bogatovnikita.popularslibsandroidgb.utils.SingleEventLiveData

class ListUsersViewModel(private val userEntityRepository: UserEntityRepository) :
    ListUsersContract.ViewModel {
    override val userLiveData: LiveData<List<UserEntity>> = MutableLiveData()
    override val errorLiveData: LiveData<Throwable> = SingleEventLiveData()
    override val progressLiveData: LiveData<Boolean> = MutableLiveData()

    override fun onRefresh() {
        loadData()
    }

    private fun loadData() {
        progressLiveData.mutable().postValue(true)
        userEntityRepository.getUsers(onSuccess = {
            progressLiveData.mutable().postValue(false)
            userLiveData.mutable().postValue(it)
        }, onError = {
            progressLiveData.mutable().postValue(false)
            errorLiveData.mutable().postValue(it)
        })
    }

    private fun <T> LiveData<T>.mutable(): MutableLiveData<T> {
        return this as? MutableLiveData<T>
            ?: throw IllegalStateException("Whaaat?")
    }
}