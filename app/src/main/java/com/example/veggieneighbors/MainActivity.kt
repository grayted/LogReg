package com.example.veggieneighbors

import android.R
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.veggieneighbors.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import java.io.ByteArrayOutputStream
import java.io.File
import java.nio.file.Files
import java.util.Arrays


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    //
    private val db = FirebaseFirestore.getInstance()




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        fetchDataFromFirestore()

        //storageDemo(view)

    }








//    private fun fetchDataFromFirestore() {
//        val collectionRef = db.collection("GB Posts")
//        val documentRef = collectionRef.document("GB_01")

    //파이어 스토어에서 데이터 끌어오기
    // 결론231120 : 안됨
    private fun fetchDataFromFirestore() {
        val collectionRef = db.collection("GB Posts")
        val documentRef = collectionRef.document("GB_01")

        documentRef.get()
            .addOnSuccessListener { document ->
                if (document.exists()) {
                    val data = document.data
                    Log.d("s1", "DocumentSnapshot data: $data")

                    // Check if "region" key exists in the data map
                    val region = data?.get("region") as? String
                    if (region != null) {
                        Log.d("s1", "Region: $region")
                        // TODO: Use the region data as needed
                    } else {
                        Log.d("f1TAG", "Region data not found in the document")
                    }

                } else {
                    Log.d("f1TAG", "No such document")
                }
            }
            .addOnFailureListener { exception ->
                Log.e("f2TAG", "Error getting document", exception)
            }
    }


}
