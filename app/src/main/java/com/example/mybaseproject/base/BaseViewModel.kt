package com.example.mybaseproject.base

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

open class BaseViewModel : ViewModel() {

    private val disposables = CompositeDisposable()
    val loadingStatus: MutableLiveData<Boolean> = MutableLiveData()
    val errorMsg: MutableLiveData<String> = MutableLiveData()
    val noNetwork: MutableLiveData<Boolean> = MutableLiveData()
    val taskCompleted: MutableLiveData<Boolean> = MutableLiveData()

    fun addDisposable(disposable: Disposable) {
        disposables.add(disposable)
    }

    override fun onCleared() {
        disposables.clear()
    }
}