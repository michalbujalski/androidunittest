package com.mb.simpleapp.utils

import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

fun <T> Observable<T>.io(): Observable<T> {
    return observeOn(Schedulers.io()).subscribeOn(AndroidSchedulers.mainThread())
}
fun Completable.ioCompletable(): Completable{
    return observeOn(Schedulers.io()).subscribeOn(AndroidSchedulers.mainThread())
}

fun <T> Single<T>.ioSingle():Single<T>{
    return observeOn(Schedulers.io()).subscribeOn(AndroidSchedulers.mainThread())
}