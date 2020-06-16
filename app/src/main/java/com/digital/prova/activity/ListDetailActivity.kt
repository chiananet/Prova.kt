package com.digital.prova.activity

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.digital.prova.R
import com.digital.prova.activity.DialogError.messageError
import com.digital.prova.adapters.DetailAdapter
import com.digital.prova.interfaces.GetDataService
import com.digital.prova.jsndata.JsnDetail
import com.digital.prova.retrofit.RetrofitClientInstance
import kotlinx.android.synthetic.main.activity_list_detail.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class ListDetailActivity : AppCompatActivity() {
    companion object {
        private const val TAG = "ListDetail"
    }
    @Suppress("DEPRECATION")
    private val progressDialog = CustomProgressDialog()
    var context: Context = this@ListDetailActivity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_detail)

        progressDialog.show(this,"Please Wait...")

        /*Create handle for the RetrofitInstance interface*/
        val service = RetrofitClientInstance.retrofitInstance?.create(GetDataService::class.java)
        val getQuery = "contents.getListAdv?fw=kidzinmind&vh=it.kidzinmind.com&lang=it&white_label=it_kidzinmind&real_customer_id=it_kim&tld=it&formats=video&category="

        val requireNonNull = Objects.requireNonNull(intent.extras)
        val queryId = requireNonNull?.getString("getIdCategory")

        val url = getQuery + queryId
        val call = service?.getDetail(url)
        call?.enqueue(object : Callback<List<JsnDetail?>?> {
            override fun onResponse(call: Call<List<JsnDetail?>?>, response: Response<List<JsnDetail?>?>) {
                progressDialog.dialog.dismiss()
                Log.e("$TAG 44", "Response = $response")
                response.body()?.let {
                    generateDataList(it)
                }
            }
            override fun onFailure(call: Call<List<JsnDetail?>?>, t: Throwable) {
                progressDialog.dialog.dismiss()
                messageError("Error!","Something went wrong...Please try later!", context)
            }
        })
    }

    /*Method to generate List of data using RecyclerView with custom adapter*/
    private fun generateDataList(detailList: List<JsnDetail?>?) {
        if (detailList!!.isNotEmpty()) {
            val adapter = DetailAdapter(this, detailList as List<JsnDetail>)
            val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(context)
            detail_recycleview.layoutManager = layoutManager
            detail_recycleview.adapter = adapter
        } else {
            Toast.makeText(context, "Error! The selected item is empty!", Toast.LENGTH_SHORT).show();
            endActivity()
        }
    }

    fun endActivity(){
        this.finish()
    }
}