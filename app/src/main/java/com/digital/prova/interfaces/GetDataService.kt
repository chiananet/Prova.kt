package com.digital.prova.interfaces

import com.digital.prova.jsndata.JsnData
import com.digital.prova.jsndata.JsnDetail
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Url

interface GetDataService {
    @get:GET("categories.getList?fw=kidzinmind&vh=it.kidzinmind.com&lang=it&white_label=it_kidzinmind&real_customer_id=it_kim&tld=it&foremats=vido")
    val posts: Call<List<JsnData?>?>?

    @GET
    fun getDetail(@Url url: String?): Call<List<JsnDetail?>?>?
}