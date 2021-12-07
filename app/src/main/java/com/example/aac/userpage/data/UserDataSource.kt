package com.example.aac.userpage.data

import com.example.aac.userpage.data.model.User
import retrofit2.Response
import retrofit2.http.GET

interface UserDataSource {
    @GET("users")
    suspend fun getUsers() : Response<List<User>>
}