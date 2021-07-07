package com.example.mybaseproject.ui.main

import android.content.Intent
import android.os.Bundle
import com.example.mybaseproject.R
import com.example.mybaseproject.base.BaseActivity
import com.example.mybaseproject.ui.second.SecondActivity
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnOpenSecond.setOnClickListener {
            startActivity(Intent(this, SecondActivity::class.java))
        }
    }
}