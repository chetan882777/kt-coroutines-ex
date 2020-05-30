package com.darshan.kotlin_coroutines

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.darshan.kotlin_coroutines.model.User
import com.darshan.kotlin_coroutines.repository.Repository

class MainViewModel : ViewModel() {

    private val _userId : MutableLiveData<String>  = MutableLiveData()

    val user: LiveData<User> = Transformations
        .switchMap(_userId){
            Repository.getUser(it)
        }

    fun setUserId(userId: String){
        if(_userId.value == userId){
            return
        }
        _userId.value = userId
    }

    fun cancelJobs(){
        Repository.jobCancel()
    }
}