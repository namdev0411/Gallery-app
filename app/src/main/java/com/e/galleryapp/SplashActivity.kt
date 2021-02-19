package com.e.galleryapp

import android.app.Activity
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.Window
import android.view.WindowInsets
import android.view.WindowManager
import androidx.core.content.ContextCompat
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_main.*

class SplashActivity : AppCompatActivity() {
    //init firebase
    private val db: FirebaseFirestore = FirebaseFirestore.getInstance();
    //inti datalist
    private var imageArr : ArrayList<Image>  = ArrayList();


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        //hide status bar and action bar
        @Suppress("DEPRECATION")
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.insetsController?.hide(WindowInsets.Type.statusBars())
        } else {
            window.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
            )
        }
        supportActionBar?.hide();

        //get data from firebase and set to girdview
        db.collection("images")
            .get()
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    for (document in task.result!!) {
                        var imageUrl : String = document.data["url"].toString();
                        var image: Image = Image(imageUrl);
                        imageArr.add(image);

                    }
                    var intent = Intent(this@SplashActivity,MainActivity::class.java)
                    intent.putExtra("imageArr",imageArr);
                    startActivity(intent);
                    finish();


                }
            }
    }
}