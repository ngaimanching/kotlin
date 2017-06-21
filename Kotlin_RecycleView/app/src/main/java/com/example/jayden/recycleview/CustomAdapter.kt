package com.example.jayden.recycleview

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

/**
 * Created by jayden on 13/6/2017.
 */

class CustomAdapter(var context: Context) : RecyclerView.Adapter<CustomAdapter.ViewHolder>() {

    var layoutInflater: LayoutInflater
    var data = arrayOf("java", "swift", "android", "ios")


    init {
        layoutInflater = LayoutInflater.from(context)
    }


    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        holder!!.textview.text = data[position]f
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {

        val view = layoutInflater.inflate(R.layout.item, parent, false)
        val viewHolder = ViewHolder(view)
        return viewHolder
    }

    override fun getItemCount(): Int {
        return data.size
    }


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textview: TextView

        init {
            textview = itemView.findViewById(R.id.textview) as TextView
        }
    }


}