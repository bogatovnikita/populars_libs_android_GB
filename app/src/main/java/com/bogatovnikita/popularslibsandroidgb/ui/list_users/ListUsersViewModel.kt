package com.bogatovnikita.popularslibsandroidgb.ui.list_users

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.bogatovnikita.popularslibsandroidgb.domain.UserEntity
import com.bogatovnikita.popularslibsandroidgb.domain.UserEntityRepository
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.kotlin.subscribeBy
import io.reactivex.rxjava3.subjects.BehaviorSubject
import io.reactivex.rxjava3.subjects.Subject

class ListUsersViewModel(private val userEntityRepository: UserEntityRepository) :
    ListUsersContract.ViewModel {
    override val userLiveData: Observable<List<UserEntity>> = BehaviorSubject.create()
    override val errorLiveData: Observable<Throwable> = BehaviorSubject.create()
    override val progressLiveData: Observable<Boolean> = BehaviorSubject.create()

    override fun onRefresh() {
        loadData()
    }

    private fun loadData() {
        progressLiveData.mutable().onNext(true)
        userEntityRepository.getUsers()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onSuccess = {
                    progressLiveData.mutable().onNext(false)
                    userLiveData.mutable().onNext(it)
                }, onError = {
                    progressLiveData.mutable().onNext(false)
                    errorLiveData.mutable().onNext(it)
                }
            )
    }

    private fun <T> LiveData<T>.mutable(): MutableLiveData<T> {
        return this as? MutableLiveData<T>
            ?: throw IllegalStateException("Whaaat?")
    }

    private fun <T : Any> Observable<T>.mutable(): Subject<T> {
        return this as? Subject<T>
            ?: throw IllegalStateException("Whaaat?")

    }
}