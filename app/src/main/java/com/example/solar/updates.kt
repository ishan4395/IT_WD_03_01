package com.example.solar

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class updates : AppCompatActivity() {

    private lateinit var editfeed : EditText
    private lateinit var updateBtn: Button
    private lateinit var deleteBtn: Button

    private lateinit var feedbackId: String
    private lateinit var dbRef : DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_updates)

        editfeed = findViewById(R.id.updatefeed)
        updateBtn = findViewById(R.id.updatebutton)
        deleteBtn = findViewById(R.id.delbutton)


        dbRef = FirebaseDatabase.getInstance().getReference("Feedbacks")

        // Retrieve the feedbackId from the intent
        feedbackId = intent.getStringExtra("feedbackId") ?: ""

        // Retrieve the feedback from the database and display it
        dbRef.child(feedbackId).get().addOnSuccessListener { snapshot ->
            val feedbackModel = snapshot.getValue(FeedbackModel::class.java)
            feedbackModel?.let {
                editfeed.setText(it.feedback)
            }
        }.addOnFailureListener { err ->
            Toast.makeText(this, "Failed to retrieve feedback. Error: ${err.message}", Toast.LENGTH_SHORT).show()
        }

        updateBtn.setOnClickListener {
            updateFeedback()
        }

        deleteBtn.setOnClickListener {
            deleteFeedback()
        }
    }

        private fun savenewfeedbak(){
            val Updateyourfeedback = editfeed.text.toString()

                if (Updateyourfeedback.isEmpty()){
                    editfeed.error ="please Enter your Feedback"

                }


    }

    private fun updateFeedback() {
        val updatedFeedback = editfeed.text.toString()

        if (updatedFeedback.isEmpty()) {
            editfeed.error = "Please enter your updated feedback"
            return
        }

        dbRef.child(feedbackId).child("feedback").setValue(updatedFeedback)
            .addOnSuccessListener {
                Toast.makeText(this, "Feedback updated successfully", Toast.LENGTH_SHORT).show()
            }
            .addOnFailureListener { err ->
                Toast.makeText(this, "Failed to update feedback. Error: ${err.message}", Toast.LENGTH_SHORT).show()
            }
    }

    private fun deleteFeedback() {
        dbRef.child(feedbackId).removeValue()
            .addOnSuccessListener {
                Toast.makeText(this, "Feedback deleted successfully", Toast.LENGTH_SHORT).show()
                finish() // Close the Updates activity
            }
            .addOnFailureListener { err ->
                Toast.makeText(this, "Failed to delete feedback. Error: ${err.message}", Toast.LENGTH_SHORT).show()
            }
    }

}