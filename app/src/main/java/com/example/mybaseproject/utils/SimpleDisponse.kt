package com.example.mybaseproject.utils

import io.reactivex.observers.DisposableObserver

/**
 * @author hungnt
 * *
 * @since 21/03/2019.
 */
open class SimpleDisposable<T> : DisposableObserver<T>() {

    override fun onComplete() {}

    override fun onError(e: Throwable) {}

    override fun onNext(t: T) {}
}