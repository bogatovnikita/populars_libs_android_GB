package com.bogatovnikita.popularslibsandroidgb.data.retrofit

import com.bogatovnikita.popularslibsandroidgb.domain.UserEntity
import com.bogatovnikita.popularslibsandroidgb.domain.UserEntityRepository
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.kotlin.subscribeBy

class RetrofitUserEntityRepositoryImplementation(val api: GithubApi) :
    UserEntityRepository {

    override fun getUsers(
        onSuccess: (List<UserEntity>) -> Unit,
        onError: ((Throwable) -> Unit)?
    ) {
        api.getUsers().subscribeBy(
            onSuccess = { onSuccess.invoke(it) },
            onError = { onError?.invoke(it) }
        )
    }

    override fun getUsers(): Single<List<UserEntity>> = api.getUsers()
}

