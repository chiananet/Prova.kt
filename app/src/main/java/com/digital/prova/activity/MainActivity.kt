@file:Suppress("DEPRECATION")

package com.digital.prova.activity

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.digital.prova.R
import com.digital.prova.activity.DialogError.messageError
import com.digital.prova.adapters.CustomAdapter
import com.digital.prova.interfaces.GetDataService
import com.digital.prova.jsndata.JsnData
import com.digital.prova.retrofit.RetrofitClientInstance
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    companion object {
        private const val TAG = "MainActivity"
    }
    private val progressDialog = CustomProgressDialog()
    var context : Context = this@MainActivity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        progressDialog.show(this,"Please Wait...")

        /*Create handle for the RetrofitInstance interface*/
        val service: GetDataService? = RetrofitClientInstance.retrofitInstance?.create(GetDataService::class.java)
        service?.posts?.enqueue(object : Callback<List<JsnData?>?> {
            override fun onResponse(call: Call<List<JsnData?>?>, response: Response<List<JsnData?>?>) {
                progressDialog.dialog.dismiss()
                Log.e("$TAG 45", "Response = $response")
                response.body()?.let {
                    generateDataList(it)
                }
            }
            override fun onFailure(call: Call<List<JsnData?>?>, t: Throwable) {
                progressDialog.dialog.dismiss()
                messageError("Error","Something went wrong...Please try later!", context)
            }
        })
    }

    @SuppressLint("WrongViewCast")
    /*Method to generate List of data using RecyclerView with custom adapter*/
    private fun generateDataList(dataList: List<JsnData?>?) {
        @Suppress("UNCHECKED_CAST") val adapter = CustomAdapter(context, dataList as List<JsnData>)
        val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(context)
        customRecyclerView.layoutManager = layoutManager
        customRecyclerView.adapter = adapter
    }
}