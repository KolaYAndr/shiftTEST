package com.example.shifttest.data.api

import com.example.shifttest.data.user.Response
import retrofit2.http.GET

interface RandomUserAPI {
    @GET("?results=5")
    suspend fun getUsers() : Response
}