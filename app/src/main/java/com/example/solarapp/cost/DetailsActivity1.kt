package com.example.solarapp.cost

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.solarapp.R
import com.google.firebase.database.FirebaseDatabase

class DetailsActivity1:AppCompatActivity() {
    private lateinit var NoOfPanels: TextView
    private lateinit var TotalAmount: TextView
    private lateinit var button2: Button
    private lateinit var button12: Button
    private lateinit var button13: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fetching_activity)

        initView()
        setValuesToViews()

        button2.setOnClickListener {
            openUpdateDialog(
                intent.getStringExtra("NoOfPanels").toString(),
                intent.getStringExtra("TotalAmount").toString()
            )
        }
        button12.setOnClickListener {
            deleteRecord(
                intent.getStringExtra("sId").toString()
            )
        }
    }

    private fun openUpdateDialog(NoOfPanels: String, TotalAmount: String) {

    }

    private fun initView() {
        NoOfPanels = findViewById(R.id.textView42)
        TotalAmount = findViewById(R.id.textView41)
        button12 = findViewById(R.id.button4)
        button2 = findViewById(R.id.button2)

    }
    private fun setValuesToViews() {
        NoOfPanels.text = intent.getStringExtra("textView42")
        TotalAmount.text = intent.getStringExtra("textView41")

    }
    private fun deleteRecord(
        sid: String
    ) {
        val dbRef = FirebaseDatabase.getInstance().getReference("Solar").child(sid)
        val mTask = dbRef.removeValue()

        mTask.addOnSuccessListener {
            Toast.makeText(this, " data deleted", Toast.LENGTH_LONG).show()
            val intent = Intent(this, FetchingActivity::class.java)
            finish()
            startActivity(intent)
        }.addOnFailureListener() { error ->
            Toast.makeText(this, "Error occur ${error.message}", Toast.LENGTH_LONG).show()
        }
    }




    private fun openUpdateDialog(
        id: String,
        NoOfPanels:String,
        TotalAmount: String

    ) {
        val mDialog = AlertDialog.Builder(this)
        val inflater = layoutInflater
        val mDialogView = inflater.inflate(R.layout.update, null)

        mDialog.setView(mDialogView)

        val textView39 = mDialogView.findViewById<EditText>(R.id.textView39)
        val textView40 = mDialogView.findViewById<EditText>(R.id.textView40)

        val button2 = mDialogView.findViewById<Button>(R.id.button2)

        textView39.setText(intent.getStringExtra("NoOfPanels").toString())
        textView40.setText(intent.getStringExtra("TotalAmount").toString())


        mDialog.setTitle("updating $NoOfPanels Record")
        val alertDialog = mDialog.create()
        alertDialog.show()

        button2.setOnClickListener {

            updateData(
                textView39.text.toString(),
                textView40.text.toString(),

                )
            Toast.makeText(applicationContext, "Data updated", Toast.LENGTH_LONG).show()

            //setting to updated data to textview

//            NoOfPanels.text = textView39.text.toString()
//            TotalAmount.text = textView40.text.toString()


            alertDialog.dismiss()
        }
    }

    private fun updateData(NoOfPanels: String, TotalAmount: String) {

    }

    private fun updateData(
        id:String,
        NoOfPanels :String,
        TotalAmount: String,

   ) {
       val dbRef = FirebaseDatabase.getInstance().getReference("Solar").child(id)
      val empInfo = SolarModel(NoOfPanels,TotalAmount)
       dbRef.setValue(empInfo)
  }
}




