package com.example.solarapp.activities


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import com.example.solarapp.R

//import com.example.virtual__homeassistance.databinding.ActivityMainBinding
//import com.example.virtual__homeassistance.databinding.ActivityMainBinding

class MainActivity2 : AppCompatActivity() {
    //private lateinit var binding: ActivityMainBinding
    //private lateinit var database: FirebaseDatabase
    private lateinit var btnupload: Button
    private lateinit var btnnext : Button
    private lateinit var btnfetch : Button
    private lateinit var imageView: ImageView

    companion object{
        val IMAGE_REQUEST_CODE = 100
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        // setContentView(R.layout.edit)
        // setContentView(R.layout.page3)
        //setContentView(R.layout.page4)

        btnnext = findViewById(R.id.button2)
        btnupload = findViewById(R.id.button)
        btnfetch = findViewById(R.id.button6)
        imageView = findViewById(R.id.imageView)

        btnnext.setOnClickListener{
            val intent = Intent(this, InsertionActivity::class.java)
            startActivity(intent)
        }
        btnupload.setOnClickListener{
            pickImageGallery()
            /*press Alt + Enter*/
        }
        btnfetch.setOnClickListener {
            val intent = Intent(this, FetchingActivity::class.java)
            startActivity(intent)
        }
    }
    private fun  pickImageGallery(){
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, IMAGE_REQUEST_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == IMAGE_REQUEST_CODE && resultCode == RESULT_OK){
            imageView.setImageURI(data?.data)
        }
    }
    //val firebase :DatabaseReference = FirebaseDatabase.getInstance().getReference()
}

