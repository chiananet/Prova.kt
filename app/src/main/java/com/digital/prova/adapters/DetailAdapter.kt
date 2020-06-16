package com.digital.prova.adapters

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.digital.prova.R
import com.digital.prova.activity.DialogError.messageError
import com.digital.prova.adapters.DetailAdapter.MyViewHolder
import com.digital.prova.jsndata.JsnDetail
import kotlinx.android.synthetic.main.detail_row.view.*

internal class DetailAdapter(private val context: Context, private var detailList: List<JsnDetail>) : RecyclerView.Adapter<MyViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailAdapter.MyViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.detail_row, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val current  = detailList[position]
        holder.setData(current, position)
    }

    override fun getItemCount(): Int = detailList.size

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private var pos = 0
        private var current: JsnDetail? = null

        fun setData(current: JsnDetail?, position: Int) {
            current.let {
                itemView.tvId.text = current?.id
                itemView.tvTitle.text = current?.title
                Log.e("$TAG 40", "current.title = ${current?.title}")
                val requestOptions = RequestOptions()
                        .error(R.drawable.ic_launcher_foreground)
                        .fitCenter()
                Glide.with(context)
                        .setDefaultRequestOptions(requestOptions)
                        .load(current?.image)
                        .into(itemView.imageView)

                itemView.setOnClickListener {
                    messageError("Title", current?.title, context)
                }
            }
            this.pos = position
            this.current = current
        }
    }
    companion object {
        private const val TAG = "DetailAdapter"
    }
}