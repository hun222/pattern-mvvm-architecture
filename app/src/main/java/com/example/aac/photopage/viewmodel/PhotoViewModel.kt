package com.example.aac.photopage.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.aac.photopage.data.PhotoRepository
import com.example.aac.photopage.data.model.Photo
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.IOException
import java.lang.Exception
import java.net.SocketException

class PhotoViewModel(private val repository: PhotoRepository) : ViewModel() {
    val mPhotos: MutableLiveData<List<Photo>> = MutableLiveData()

    fun getPhotos() {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                repository.getPhotos()?.let {
                    if(it.isSuccessful) {
                        it.body()?.let {
                            mPhotos.postValue(it)
                        }
                    }
                }
            } catch (e: SocketException) {
                e.printStackTrace()
            } catch (e: IOException) {
                e.printStackTrace()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}

class PhotoViewModelFactory(private val repository: PhotoRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PhotoViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return PhotoViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}