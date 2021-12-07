package com.example.aac.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClient {
    private val BASE_URL = "https://jsonplaceholder.typicode.com"

    private fun getRetrofit() : Retrofit {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return retrofit
    }

    companion object {
        private var INSTANCE: Retrofit ?= null

        @JvmStatic
        fun getInstance() =
            INSTANCE ?: synchronized(RetrofitClient::class.java) {
                INSTANCE ?: RetrofitClient().getRetrofit()
            }
    }
}