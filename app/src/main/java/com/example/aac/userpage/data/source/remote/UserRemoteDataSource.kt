package com.example.aac.userpage.data.source.remote

import com.example.aac.retrofit.RetrofitClient
import com.example.aac.userpage.data.UserDataSource
import com.example.aac.userpage.data.model.User
import retrofit2.Response

class UserRemoteDataSource private constructor() : UserDataSource {

    override suspend fun getUsers(): Response<List<User>> {
        val retrofitClient = RetrofitClient.getInstance()
            .create(UserDataSource::class.java)
        return retrofitClient.getUsers()
    }

    companion object {
        private var INSTANCE: UserRemoteDataSource ?= null

        @JvmStatic
        fun getInstance() =
            INSTANCE ?: synchronized(UserRemoteDataSource::class.java) {
                INSTANCE ?: UserRemoteDataSource().also { INSTANCE = it }
            }
    }
}