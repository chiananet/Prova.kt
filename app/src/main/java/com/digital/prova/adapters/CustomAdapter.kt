package com.digital.prova.adapters

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.digital.prova.activity.ListDetailActivity
import com.digital.prova.R
import com.digital.prova.adapters.CustomAdapter.MyViewHolder
import com.digital.prova.jsndata.JsnData
import kotlinx.android.synthetic.main.custom_row.view.*

internal class CustomAdapter(private val context: Context, private var dataList: List<JsnData>) : RecyclerView.Adapter<MyViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.custom_row, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val current = dataList[position]
        holder.setData(current, position)
    }

    override fun getItemCount(): Int = dataList.size

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private var pos = 0
        private var current: JsnData? = null

        fun setData(current: JsnData?, position: Int) {
            current.let {
                itemView.tvCategory.text = current?.idCategory
                itemView.tvName.text = current?.name
                Log.e("$TAG 41", "current.name = ${current?.name}")
                val requestOptions = RequestOptions()
                        .error(R.drawable.ic_launcher_foreground)
                        .fitCenter()
                Glide.with(context)
                        .setDefaultRequestOptions(requestOptions)
                        .load(current?.imageCat)
                        .into(itemView.imgCategory)

                itemView.setOnClickListener {
                    val intent = Intent(context, ListDetailActivity::class.java)
                    intent.putExtra("getIdCategory", current?.idCategory)
                    context.startActivity(intent)
                }
            }
            this.pos = position
            this.current = current
        }
    }
    companion object {
        private const val TAG = "CustomAdapter"
    }
}