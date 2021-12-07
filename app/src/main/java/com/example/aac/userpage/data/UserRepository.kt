package com.example.aac.userpage.data

import com.example.aac.userpage.data.model.User
import com.example.aac.userpage.data.source.remote.UserRemoteDataSource
import retrofit2.Response

class UserRepository private constructor() : UserDataSource {
    private var dataSource: UserRemoteDataSource

    init {
        dataSource = UserRemoteDataSource.getInstance()
    }

    override suspend fun getUsers(): Response<List<User>> {
        return dataSource.getUsers()
    }

    companion object {
        private var INSTANCE: UserRepository ?= null

        @JvmStatic
        fun getInstance() =
            INSTANCE ?: synchronized(UserRepository::class.java) {
                INSTANCE
                    ?: UserRepository().also { INSTANCE = it }
            }
    }
}