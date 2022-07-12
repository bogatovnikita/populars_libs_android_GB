package com.bogatovnikita.popularslibsandroidgb.ui.list_users

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bogatovnikita.popularslibsandroidgb.domain.UserEntity
import com.bogatovnikita.popularslibsandroidgb.domain.UserEntityRepository
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.kotlin.subscribeBy
import io.reactivex.rxjava3.subjects.BehaviorSubject
import io.reactivex.rxjava3.subjects.Subject

class ListUsersViewModel(private val userEntityRepository: UserEntityRepository) :
    ListUsersContract.ViewModel, ViewModel() {
    override val userObservable: Observable<List<UserEntity>> = BehaviorSubject.create()
    override val errorObservable: Observable<Throwable> = BehaviorSubject.create()
    override val progressObservable: Observable<Boolean> = BehaviorSubject.create()

    override fun onRefresh() {
        loadData()
    }

    private fun loadData() {
        progressObservable.mutable().onNext(true)
        userEntityRepository.getUsers()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onSuccess = {
                    progressObservable.mutable().onNext(false)
                    userObservable.mutable().onNext(it)
                }, onError = {
                    progressObservable.mutable().onNext(false)
                    errorObservable.mutable().onNext(it)
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