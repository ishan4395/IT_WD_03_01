package com.example.solar

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.*

class viewfeedbacks : AppCompatActivity() {

    private lateinit var feedbackRecyclerView: RecyclerView
    private lateinit var feedbackList: ArrayList<FeedbackModel>
    private lateinit var dbRef: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_viewfeedbacks)

        feedbackRecyclerView = findViewById(R.id.feedbacksrv)
        feedbackRecyclerView.layoutManager = LinearLayoutManager(this)
        feedbackRecyclerView.setHasFixedSize(true)

        feedbackList = arrayListOf<FeedbackModel>()

        getFeedbackData()
    }

    private fun getFeedbackData(){
        feedbackRecyclerView.visibility = View.GONE

        dbRef = FirebaseDatabase.getInstance().getReference("Feedbacks")

        dbRef.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                feedbackList.clear()
                if(snapshot.exists()){
                    for (feedbackSnap in snapshot.children){
                        val feedbackData = feedbackSnap.getValue(FeedbackModel::class.java)
                        feedbackList.add(feedbackData!!)
                    }
                    val mAdapater = fbackAdapater(feedbackList)
                    feedbackRecyclerView.adapter = mAdapater

                    feedbackRecyclerView.visibility = View.VISIBLE

                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }
}