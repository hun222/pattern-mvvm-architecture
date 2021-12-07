package com.example.aac.photopage.data.source.remote

import com.example.aac.photopage.data.PhotoDataSource
import com.example.aac.photopage.data.model.Photo
import com.example.aac.retrofit.RetrofitClient
import retrofit2.Response

class PhotoRemoteDataSource private constructor() : PhotoDataSource {
    override suspend fun getPhotos(): Response<List<Photo>> {
        val retrofitClient = RetrofitClient.getInstance()
            .create(PhotoDataSource::class.java)
        return retrofitClient.getPhotos()
    }

    companion object {
        private var INSTANCE: PhotoRemoteDataSource?= null

        @JvmStatic
        fun getInstance() =
            INSTANCE ?: synchronized(PhotoRemoteDataSource::class.java) {
                INSTANCE ?: PhotoRemoteDataSource().also { INSTANCE = it }
            }
    }
}