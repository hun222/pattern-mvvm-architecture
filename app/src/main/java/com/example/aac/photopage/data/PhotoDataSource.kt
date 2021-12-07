package com.example.aac.photopage.data

import com.example.aac.photopage.data.model.Photo
import com.example.aac.userpage.data.model.User
import retrofit2.Response
import retrofit2.http.GET

interface PhotoDataSource {
    @GET("photos")
    suspend fun getPhotos() : Response<List<Photo>>
}