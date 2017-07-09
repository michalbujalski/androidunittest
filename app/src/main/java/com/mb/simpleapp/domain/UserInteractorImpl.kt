package com.mb.simpleapp.domain

import android.util.Log
import com.mb.simpleapp.data.UserData
import com.mb.simpleapp.data.UserDataStore
import io.reactivex.Completable
import io.reactivex.Single
import java.util.concurrent.TimeUnit

class UserInteractorImpl(val userDataStore: UserDataStore) : UserInteractor {
    override fun login(login:String, password:String): Completable {
        val userData = UserData("Tom","tom@example.com")
        return Single
                .just(userData)
                .map{userDataStore.save(userData)}
                .delay(3,TimeUnit.SECONDS)
                .toCompletable()
    }
}