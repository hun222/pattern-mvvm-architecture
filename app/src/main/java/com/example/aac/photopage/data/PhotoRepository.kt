package com.example.aac.photopage.data

import com.example.aac.photopage.data.model.Photo
import com.example.aac.photopage.data.source.remote.PhotoRemoteDataSource
import com.example.aac.userpage.data.UserRepository
import com.example.aac.userpage.data.source.remote.UserRemoteDataSource
import retrofit2.Response

class PhotoRepository private constructor() : PhotoDataSource {
    private var dataSource: PhotoRemoteDataSource

    init {
        dataSource = PhotoRemoteDataSource.getInstance()
    }

    override suspend fun getPhotos(): Response<List<Photo>> {
        return dataSource.getPhotos()
    }

    companion object {
        private var INSTANCE: PhotoRepository?= null

        @JvmStatic
        fun getInstance() =
            INSTANCE ?: synchronized(PhotoRepository::class.java) {
                INSTANCE
                    ?: PhotoRepository().also { INSTANCE = it }
            }
    }
}