package com.example.mybaseproject.data.api

import retrofit2.Retrofit
import retrofit2.SimpleXmlConverterFactory
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory

class RetrofitService {
    companion object {
        fun getRetrofitInstance(): ApiService {
            val retrofit = Retrofit.Builder()
                .baseUrl("")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(SimpleXmlConverterFactory.create())
                .build()

            return retrofit.create(ApiService::class.java)
        }
    }
}