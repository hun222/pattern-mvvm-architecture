package com.example.aac.userpage.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.aac.userpage.data.UserRepository
import com.example.aac.userpage.data.model.User
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.IOException
import java.lang.Exception
import java.net.SocketException

class UserViewModel(private val repository: UserRepository) : ViewModel() {

    val mUsers: MutableLiveData<List<User>> = MutableLiveData()

    fun getUsers() {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                repository.getUsers()?.let {
                    if(it.isSuccessful) {
                        it.body()?.let {
                            mUsers.postValue(it)
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

class UserViewModelFactory(private val repository: UserRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(UserViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return UserViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}