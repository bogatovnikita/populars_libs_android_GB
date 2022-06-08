package com.bogatovnikita.popularslibsandroidgb

import android.os.Handler
import android.os.Looper

class FakeUserEntityImplementation : UserEntityRepository {

    private val data: List<UserEntity> = listOf(
        UserEntity("Vladimir Puptin", 1, "https://avatars.githubusercontent.com/u/1?v=4"),
        UserEntity("Dmitriy Medvedkin", 2, "https://avatars.githubusercontent.com/u/2?v=4"),
        UserEntity("Boris Yeltsin", 3, "https://avatars.githubusercontent.com/u/3?v=4")
    )


    override fun getUsers(onSuccess: (List<UserEntity>) -> Unit, onError: ((Throwable) -> Unit)?) {
        Handler(Looper.getMainLooper()).postDelayed({ onSuccess(data) }, DATA_LOADING_FAKE_DELAY)
    }

    companion object {
        const val DATA_LOADING_FAKE_DELAY = 4000L
    }
}