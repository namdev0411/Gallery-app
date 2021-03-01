package com.e.galleryapp

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.github.dhaval2404.imagepicker.ImagePicker
import kotlinx.android.synthetic.main.activity_modal_view.*

class ModalViewActivity : AppCompatActivity() {
    companion object {
        const val REQUEST_CAMERA = 1001;
        const val REQUEST_GALLERY = 1002;
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_modal_view)
        initUI();

    }
    private fun initUI(){
        chooseImage.setOnClickListener {
            pickImageFromGallery()
        }
        openCamera.setOnClickListener {
            captureImageUsingCamera();
        }
    }
    private fun pickImageFromGallery(){
        ImagePicker.with(this)
            .crop()	    			//Crop image(Optional), Check Customization for more option
            .compress(1024)			//Final image size will be less than 1 MB(Optional)
            .maxResultSize(1080, 1080)	//Final image resolution will be less than 1080 x 1080(Optional)
            .start(REQUEST_GALLERY)
    }
    private fun captureImageUsingCamera(){
        Log.d("Loi","Helooooooooooooooo");
        ImagePicker.with(this)
            .cameraOnly()	//User can only capture image using Camera
            .start(REQUEST_CAMERA)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode== Activity.RESULT_OK){
            var imageUri = data!!.data!!;
            when(requestCode){
                REQUEST_CAMERA->{
                    Log.e("itent",intent.dataString!!);
                }
                REQUEST_GALLERY->{
                    FirebaseStorageManager().uploadImage(imageUri);
                }
            }
        }
    }
}