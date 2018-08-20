package com.mb.simpleapp.domain.users

import com.mb.simpleapp.data.users.UserData
import com.mb.simpleapp.data.users.UserDataStore
import io.reactivex.Completable
import io.reactivex.Single
import java.util.concurrent.TimeUnit

class UserInteractorImpl(val userDataStore: UserDataStore) : UserInteractor {
    override fun login(login:String, password:String): Single<Boolean> {
        val userData = UserData("Tom", "tom@example.com")
        return Single
                .just(userData)
                .map{userDataStore.save(userData)}
                .delay(3,TimeUnit.SECONDS)
                .map { true }
    }
}