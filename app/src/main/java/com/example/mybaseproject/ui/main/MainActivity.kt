package com.example.mybaseproject.ui.main

import android.os.Bundle
import com.example.mybaseproject.R
import com.example.mybaseproject.base.BaseActivity

class MainActivity: BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}