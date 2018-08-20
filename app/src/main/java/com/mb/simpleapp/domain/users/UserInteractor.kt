package com.mb.simpleapp.domain.users

import io.reactivex.Completable
import io.reactivex.Single

interface UserInteractor {
    fun login(login: String, password: String): Single<Boolean>
}