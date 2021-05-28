package com.example.mybaseproject.app

import android.R.id
import android.app.Application
import android.os.Bundle
import com.example.mybaseproject.BuildConfig
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.messaging.FirebaseMessaging
import timber.log.Timber
import timber.log.Timber.DebugTree


class App : Application() {

    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            Timber.plant(DebugTree())
        }

        FirebaseMessaging.getInstance().isAutoInitEnabled = true


    }
}