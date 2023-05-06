package com.example.solar

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class Feedbacks : AppCompatActivity() {

    private lateinit var enterFback: EditText
    private lateinit var submitFback: Button
    private lateinit var dbRef: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_feedbacks)

        enterFback = findViewById(R.id.enterFeedback)
        submitFback = findViewById(R.id.submitFeedback)
        dbRef = FirebaseDatabase.getInstance().getReference("Feedbacks")

        submitFback.setOnClickListener{
            saveFeedback()
        }
    }

    private fun saveFeedback() {
        //getting values
        val feedback = enterFback.text.toString()

        if (feedback.isEmpty()){
            submitFback.error = "Please enter your feedback"
        }

        val feedbackId = dbRef.push().key!!

        val feedbackModel = FeedbackModel(feedbackId, feedback)

        dbRef.child(feedbackId).setValue(feedbackModel)
            .addOnCompleteListener {
                // Display success message
                Toast.makeText(this, "Data inserted successfully", Toast.LENGTH_SHORT).show()

                // Redirect to Updates activity
                val intent = Intent(this, updates::class.java)
                intent.putExtra("feedbackId", feedbackId)
                startActivity(intent)
            }.addOnFailureListener{err ->
                Toast.makeText(this, "Data inserted unsuccessfully. Error ${err.message}", Toast.LENGTH_SHORT).show()
            }
    }
}