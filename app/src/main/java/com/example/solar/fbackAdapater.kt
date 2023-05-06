package com.example.solar

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class fbackAdapater (private val feedbackList: ArrayList<FeedbackModel>) :
    RecyclerView.Adapter<fbackAdapater.ViewHolder>() {

    //Get All Feedbacks
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): fbackAdapater.ViewHolder {
        val feedbackView = LayoutInflater.from(parent.context)
            .inflate(R.layout.all_feedbacks, parent, false)
        return ViewHolder(feedbackView)
    }

    override fun onBindViewHolder(holder: fbackAdapater.ViewHolder, position: Int) {
        val currentFeedback = feedbackList[position]
        holder.allFeedback.text = currentFeedback.feedback
    }

    override fun getItemCount(): Int {
        return feedbackList.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val allFeedback : TextView = itemView.findViewById(R.id.allFeedback)
    }


}