package com.bogatovnikita.popularslibsandroidgb.data

import android.os.Handler
import android.os.Looper
import com.bogatovnikita.popularslibsandroidgb.R
import com.bogatovnikita.popularslibsandroidgb.domain.UserEntity
import com.bogatovnikita.popularslibsandroidgb.domain.UserEntityRepository

class FakeUserEntityImplementation : UserEntityRepository {

    private val data: MutableList<UserEntity> = mutableListOf(
        UserEntity(
            "Vladimir Puptin",
            1,
            "https://avatars.githubusercontent.com/u/1?v=4",
            R.string.description_user
        ),
        UserEntity(
            "Dmitriy Medvedkin",
            2,
            "https://avatars.githubusercontent.com/u/2?v=4",
            R.string.description_user
        ),
        UserEntity(
            "Boris Yeltsin",
            3,
            "https://avatars.githubusercontent.com/u/3?v=4",
            R.string.description_user
        )
    )


    override fun getUsers(
        onSuccess: (MutableList<UserEntity>) -> Unit,
        onError: ((Throwable) -> Unit)?
    ) {
        Handler(Looper.getMainLooper()).postDelayed({ onSuccess(data) }, DATA_LOADING_FAKE_DELAY)
    }

    companion object {
        const val DATA_LOADING_FAKE_DELAY = 4000L
    }
}