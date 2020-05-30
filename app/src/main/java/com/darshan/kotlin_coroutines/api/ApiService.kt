package com.darshan.kotlin_coroutines.api

import com.darshan.kotlin_coroutines.model.User
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService{

    @GET("placeholder/user/{userId}")
    suspend fun getUser(
        @Path( "userId") userId : String
    ): User
}