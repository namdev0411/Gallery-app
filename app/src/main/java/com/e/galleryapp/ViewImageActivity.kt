package com.e.galleryapp

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import android.view.WindowInsets
import android.view.WindowManager
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_viewimage.*

class ViewImageActivity : AppCompatActivity() {
    private var viewImageAdappter : ViewImageAdappter  ? =  null;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_viewimage)

        //hide status bar and action bar
        supportActionBar?.hide();
        if(Build.VERSION.SDK_INT>=21){
            val window : Window = this@ViewImageActivity.window;
            window.statusBarColor = ContextCompat.getColor(this@ViewImageActivity,R.color.bar);
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
        var position : Int = intent.getSerializableExtra("position") as Int;
        var imageArr  = intent.getSerializableExtra("imageArr") as ArrayList<Image>;

        if (imageArr != null) {
            viewImageAdappter = ViewImageAdappter(this,imageArr);
            viewImageList?.adapter = viewImageAdappter;
        }
        viewImageList.setCurrentItem(position,false);
    }
}