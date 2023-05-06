package com.example.solarapp.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
    import com.example.solarapp.R
    import com.example.solarapp.models.ModelHome
    import com.google.firebase.database.FirebaseDatabase

    class DetailsActivity: AppCompatActivity() {
        private lateinit var tvEmpId: TextView
        private lateinit var tvEmpName: TextView
        private lateinit var tvEmpAge: TextView
        private lateinit var tvEmpSalary: TextView
        private lateinit var tvSquare : TextView
        private lateinit var btnUpdate: Button
        private lateinit var btnDelete: Button

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_details)

            initView()
            setValuesToViews()

            btnUpdate.setOnClickListener{
                openUpdateDialog(
                    intent.getStringExtra("cusId").toString(),
                    intent.getStringExtra("type").toString()
                )
            }
            btnDelete.setOnClickListener {
                deleteRecord(
                    intent.getStringExtra("cusId").toString()
                )
            }

        }
        private fun deleteRecord(
            id: String
        ){
            val dbRef = FirebaseDatabase.getInstance().getReference("Homes").child(id)
            val mTask = dbRef.removeValue()

            mTask.addOnSuccessListener {
                Toast.makeText(this, " data deleted", Toast.LENGTH_LONG).show()
                val intent = Intent(this, FetchingActivity::class.java)
                finish()
                startActivity(intent)}.addOnFailureListener(){error ->
                Toast.makeText(this, "Error occur ${error.message}", Toast.LENGTH_LONG).show()
            }
        }
        private fun initView() {
            tvEmpId = findViewById(R.id.tvCusId)
            tvEmpName = findViewById(R.id.tvEmpId)
            tvEmpAge = findViewById(R.id.tvEmpName)
            tvEmpSalary = findViewById(R.id.tvEmpAge)
            tvSquare = findViewById(R.id.tvEmpSalary)
            btnUpdate = findViewById(R.id.btnUpdate)
            btnDelete = findViewById(R.id.btnDelete)
        }
        private fun setValuesToViews() {
            tvEmpId.text = intent.getStringExtra("cusId")
            tvEmpName.text = intent.getStringExtra("type")
            tvEmpAge.text = intent.getStringExtra("colour")
            tvEmpSalary.text = intent.getStringExtra("manufacturer country")
            tvSquare.text = intent.getStringExtra("square feet")
        }
        private fun openUpdateDialog(
            cusId:String,
            type : String

        ){
            val mDialog =  AlertDialog.Builder(this)
            val inflater = layoutInflater
            val mDialogView = inflater.inflate(R.layout.update_dialog,null)

            mDialog.setView(mDialogView)

            val ettype = mDialogView.findViewById<EditText>(R.id.etEmpName)
            val etcolor = mDialogView.findViewById<EditText>(R.id.etEmpAge)
            val etMc= mDialogView.findViewById<EditText>(R.id.etEmpSalary)
            val etSquare = mDialogView.findViewById<EditText>(R.id.etEmpSquare)
            val etupdate = mDialogView.findViewById<Button>(R.id.btnUpdateData)

            ettype.setText(intent.getStringExtra("type").toString())
            etcolor.setText(intent.getStringExtra("colour").toString())
            etMc.setText(intent.getStringExtra("manufacturer country").toString())
            etSquare.setText(intent.getStringExtra("square feet").toString())

            mDialog.setTitle("updating $type Record")
            val alertDialog = mDialog.create()
            alertDialog.show()

            etupdate.setOnClickListener{
                updateData(
                    cusId,
                    ettype.text.toString(),
                    etcolor.text.toString(),
                    etMc.text.toString(),
                    etSquare.text.toString()
                )
            }
            Toast.makeText(applicationContext,"Data updated", Toast.LENGTH_LONG).show()

            //setting to updated data to textview

            tvEmpName.text =  ettype.text.toString()
            tvEmpAge.text =  etcolor.text.toString()
            tvEmpSalary.text = etMc.text.toString()
            tvSquare.text = etSquare.text.toString()

            alertDialog.dismiss()
        }
        ;private fun updateData(
            id:String,
            type :String,
            color: String,
            manufacturercoun :String,
            square : String
        ) {
            val dbRef = FirebaseDatabase.getInstance().getReference("Homes").child(id)
            val empInfo = ModelHome(id,type,color,manufacturercoun,square)
            dbRef.setValue(empInfo)
        }
    }


