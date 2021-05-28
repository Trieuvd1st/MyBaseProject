package com.example.mybaseproject.utils

import com.example.mybaseproject.domain.pojo.response.HttpError
import com.google.gson.Gson
import retrofit2.HttpException
import retrofit2.Response
import timber.log.Timber

/**
 * @author hungnt
 */
open class RetrofitDisposable<T> : SimpleDisposable<T>() {

    override fun onComplete() {}

    /**
     * Do not override this method in derived class, just implement [onHttpError] and [onNetworkError]
     * method to handle [Error] response
     */
    final override fun onError(e: Throwable) {

        Timber.d("Error response: ${e.message}")

        if (e is HttpException) {
            val error = parseError(e.response())

            //onHttpError(HttpError(error.messageCode, ApiErrorCode.parseMsg(error.messageCode), error.message))
            return
        }

        onNetworkError(e)
    }

    override fun onNext(t: T) {}

    /**
     * Callback for handling Http response error (statusCode >= 400)
     */
    open fun onHttpError(httpError: HttpError) {}

    /**
     * Internet connection is not available.
     */
    open fun onNetworkError(e: Throwable) {}

    private fun parseError(response: Response<*>): Error {
        val error: Error

        error = try {
            val gson = Gson()
            gson.fromJson(response.errorBody()?.string(), Error::class.java)
        } catch (e: Exception) {
            Error()
        }

        return error
    }
}