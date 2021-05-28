package com.example.mybaseproject.utils

import com.example.mybaseproject.domain.pojo.response.HttpError
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

fun <T> Observable<T>.observeOnMain(): Observable<T> {
    return this.subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
}

fun <T> Observable<T>.subscribeWith(
    onNext: (t: T) -> Unit,
    onHttpError: (httpError: HttpError) -> Unit,
    onNetworkError: (e: Throwable) -> Unit
): Disposable {

    return subscribeWith(object : RetrofitDisposable<T>() {
        override fun onNext(t: T) {
            onNext(t)
        }

        override fun onHttpError(httpError: HttpError) {
            onHttpError(httpError)
        }

        override fun onNetworkError(e: Throwable) {
            onNetworkError(e)
        }
    })
}