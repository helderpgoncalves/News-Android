package com.example.newsdemoapp

import android.content.DialogInterface
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.news_row.view.*
import android.content.Intent
import android.support.v4.content.ContextCompat.startActivity

import android.net.Uri
import android.support.v4.content.ContextCompat
import android.content.Context
import android.support.v4.content.ContextCompat.startActivity
import java.lang.String

class MainAdapter(private val newsFeed: Api, context: Context) : RecyclerView.Adapter<CustomViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): CustomViewHolder {
        val layoutInflater = LayoutInflater.from(parent?.context)
        val cellForRow = layoutInflater.inflate(R.layout.news_row, parent, false)
        return CustomViewHolder(cellForRow)
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        val news = newsFeed.list[position]

        val imageView = holder.itemView.image
        Picasso
            .get()
            .load(news.image)
            .noFade()
            .into(imageView)

        holder.itemView.title.text = news.title
        holder.itemView.time.text = news.time
        holder.itemView.url.text = news.url
        holder.itemView.mediasource.text = news.mediasource

        holder.itemView.url.setOnClickListener(View.OnClickListener {
            val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(holder.itemView.url.text.toString()))
            holder.itemView.url.context.startActivity(browserIntent)
        })
    }

    override fun getItemCount(): Int {
        return newsFeed.list.size
    }
}

class CustomViewHolder(v: View) : RecyclerView.ViewHolder(v)