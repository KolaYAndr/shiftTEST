package com.example.shifttest.data.retrofit

import com.example.shifttest.data.api.RandomUserAPI
import com.example.shifttest.data.interceptor.OkHttpInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitProvider {
    private const val BASE_ENDPOINT = "https://randomuser.me/api/"

    fun provideRetrofit(): Retrofit = Retrofit.Builder()
        .baseUrl(BASE_ENDPOINT)
        .client(OkHttpInterceptor.provideOkHttpClient(OkHttpInterceptor.createInterceptor()))
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun connectApi(retrofit: Retrofit): RandomUserAPI = retrofit.create(RandomUserAPI::class.java)
}