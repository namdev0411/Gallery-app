package com.e.galleryapp
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class ImageAdapter(var context: Context,var dataArr : ArrayList<Image>) : RecyclerView.Adapter<ImageAdapter.ItemHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
       val itemHolder = LayoutInflater.from(parent.context).inflate(R.layout.row_item,parent,false);
        return ItemHolder(itemHolder);
    }

    override fun getItemCount(): Int {
        return dataArr.size;
    }

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        Glide.with(context)
            .load(dataArr.get(position).url)
            .into(holder.image);

        holder.image.setOnClickListener {
            var intent = Intent(context,ViewimageActivity::class.java)
            intent.putExtra("image",dataArr.get(position));
            startActivity(context,intent,null);
        }
    }

    class ItemHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        var image : ImageView = itemView.findViewById<ImageView>(R.id.imageView);
    }

}
