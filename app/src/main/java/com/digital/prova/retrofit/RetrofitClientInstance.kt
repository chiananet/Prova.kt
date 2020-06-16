package com.digital.prova.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClientInstance {
    private const val TAG = "RetrofitClientInstance"
    private const val BASE_URL = "http://www.kidzinmind.com/it/static_env/lapis/apps/"

    private var retrofit: Retrofit? = Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build()

    val retrofitInstance: Retrofit? get() { return  retrofit }
}