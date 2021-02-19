package com.e.galleryapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class ViewImageAdappter(var context : Context, var dataArr: ArrayList<Image>) : RecyclerView.Adapter<ViewImageAdappter.ItemHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        var itemHolder = LayoutInflater.from(parent.context).inflate(R.layout.activity_viewimage_item,parent,false);
        return ItemHolder(itemHolder);
    }

    override fun getItemCount(): Int {
        return  dataArr.size;
    }

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        Glide.with(context)
            .load(dataArr.get(position).url)
            .into(holder.imageItem);
    }
    class ItemHolder(view : View) : RecyclerView.ViewHolder(view){
        val imageItem  = view.findViewById<ImageView>(R.id.viewImageItem);
    }
}