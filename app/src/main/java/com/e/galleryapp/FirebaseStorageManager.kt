package com.e.galleryapp

import android.content.Context
import android.net.Uri
import android.util.Log
import android.widget.Toast
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference

class FirebaseStorageManager {
    private val root: StorageReference = FirebaseStorage.getInstance().getReference();

    val imagesRef = root.child("images");

    fun uploadImage  (imageURI : Uri):String{
        Log.e("chekcUri",imageURI.toString());
        var downloadUri : Uri? = null;
        imagesRef.child("images/${imageURI.lastPathSegment}").putFile(imageURI)
            .addOnCompleteListener{task ->
                if (task.isSuccessful) {
                    downloadUri = task.result as Uri;
                }
            }
        return "Ok";
    }
}