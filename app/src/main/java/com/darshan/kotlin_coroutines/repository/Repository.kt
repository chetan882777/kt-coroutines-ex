package com.darshan.kotlin_coroutines.repository

import androidx.lifecycle.LiveData
import com.darshan.kotlin_coroutines.api.RetrofitBuilder
import com.darshan.kotlin_coroutines.model.User
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main

object Repository{
    var job : CompletableJob? = null

    fun getUser(userId: String) : LiveData<User> {
        job = Job()
        return object : LiveData<User>(){
            override fun onActive() {
                super.onActive()
                job?.let { thisJob ->
                    CoroutineScope(IO + thisJob).launch {
                        val user = RetrofitBuilder.apiService.getUser(userId)
                        withContext(Main){
                            value = user
                            thisJob.complete()
                        }
                    }
                }
            }
        }
    }

    fun jobCancel(){
        job?.cancel()
    }
}