package com.mb.simpleapp.domain

import io.reactivex.Completable

interface UserInteractor {
    fun login(login: String, password: String): Completable
}