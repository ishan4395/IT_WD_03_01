package com.example.solarapp.cost

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.solarapp.R
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class MainActivity : AppCompatActivity() {

    private lateinit var button2:Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.update)

        button2 = findViewById(R.id.button2)

        button2.setOnClickListener{
            val intent = Intent(this,Add::class.java)
            startActivity(intent)
        }



        val firebase: DatabaseReference = FirebaseDatabase.getInstance().getReference()


    }

}