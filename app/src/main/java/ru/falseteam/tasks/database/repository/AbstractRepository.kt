package ru.falseteam.tasks.database.repository

import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers

abstract class AbstractRepository {
    protected fun <T> asyncSingle(from: () -> T) = Single.fromCallable(from).subscribeOn(Schedulers.io())
    protected fun asyncCompletable(from: () -> Unit) = Completable.fromCallable(from).subscribeOn(Schedulers.io())
}