package com.e.galleryapp

import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Window
import android.view.WindowInsets
import android.view.WindowManager
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_viewimage.*
import java.net.URI

class ViewimageActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_viewimage)

        //hide status bar and action bar
        supportActionBar?.hide();
        if(Build.VERSION.SDK_INT>=21){
            val window : Window = this@ViewimageActivity.window;
            window.statusBarColor = ContextCompat.getColor(this@ViewimageActivity,R.color.bar);
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.insetsController?.hide(WindowInsets.Type.statusBars())
        } else {
            window.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
            )
        }
        //getdata from MainActivity
        var imageItem : Image = intent.getSerializableExtra("image") as Image;
        //load image from url set to view
        Glide.with(this@ViewimageActivity)
            .load(imageItem.url)
            .into(viewImage)
    }
}