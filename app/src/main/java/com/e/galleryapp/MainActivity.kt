package com.e.galleryapp

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.Window
import android.widget.Toast

import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.toolbar.*

class MainActivity : AppCompatActivity() {
    private var recyclerView: RecyclerView ? = null;
    private var gridLayoutManager: GridLayoutManager ? = null;
    private var imageAdapter:ImageAdapter ? = null;

    override fun onCreate(savedInstanceState: Bundle?) {
        if(Build.VERSION.SDK_INT>=21){
           val window : Window = this@MainActivity.window;
            window.statusBarColor = ContextCompat.getColor(this,R.color.bar);
        }
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.gridImage);
        gridLayoutManager = GridLayoutManager(this@MainActivity,3,LinearLayoutManager.VERTICAL,false);
        recyclerView?.layoutManager = gridLayoutManager;
        recyclerView?.setHasFixedSize(true);
        var imageArr  = intent.getSerializableExtra("imageArr") as ArrayList<Image>;

        if (imageArr != null) {
            imageAdapter = ImageAdapter(this@MainActivity,imageArr);
            recyclerView?.adapter = imageAdapter;
        }

        //refresh if swipe
        swipeToRefresh.setOnRefreshListener {
            Toast.makeText(this,"re",Toast.LENGTH_SHORT)
            swipeToRefresh.isRefreshing = false;
        }
        //
        addBtn.setOnClickListener{view->
                startActivity(Intent(this@MainActivity,ModalViewActivity::class.java));
        }
    }
}