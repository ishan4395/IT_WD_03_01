package com.example.solarapp.activities


import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.solarapp.R
import com.example.solarapp.models.ModelHome
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class InsertionActivity : AppCompatActivity() {

    private lateinit var etEmpName: EditText
    private lateinit var etEmpAge: EditText
    private lateinit var etEmpSalary: EditText
    private lateinit var etEmpSquare: EditText
    private lateinit var btnSaveData: Button

    private lateinit var dbRef: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_insertion)

        etEmpName = findViewById(R.id.etEmpName)
        etEmpAge = findViewById(R.id.etEmpAge)
        etEmpSalary = findViewById(R.id.etEmpSalary)
        etEmpSquare = findViewById(R.id.editTextTextPersonName6)
        btnSaveData = findViewById(R.id.btnSave)

        dbRef = FirebaseDatabase.getInstance().getReference("Homes")

        btnSaveData.setOnClickListener {
            saveData()
        }
    }

    private fun saveData() {

        //getting values
        val htype = etEmpName.text.toString()
        val hcolor = etEmpAge.text.toString()
        val hmanufactordate = etEmpSalary.text.toString()
        val hsqurefeet = etEmpSquare.text.toString()

        if (htype.isEmpty()) {
            etEmpName.error = "Please enter type"
        }
        if (hcolor.isEmpty()) {
            etEmpAge.error = "Please enter color"
        }
        if (hmanufactordate.isEmpty()) {
            etEmpSalary.error = "Please enter manufacturer country"
        }
        if(hsqurefeet.isEmpty()){
            etEmpSquare.error = "Please enter square feet"
        }
        val cusId = dbRef.push().key!!

        val emp = ModelHome(cusId,htype,hcolor, hmanufactordate  ,hsqurefeet)

        dbRef.child(cusId).setValue(emp)
            .addOnCompleteListener {
                Toast.makeText(this, "Data inserted successfully", Toast.LENGTH_LONG).show()

                etEmpName.text.clear()
                etEmpAge.text.clear()
                etEmpSalary.text.clear()
                etEmpSquare.text.clear()


            }.addOnFailureListener { err ->
                Toast.makeText(this, "Error ${err.message}", Toast.LENGTH_LONG).show()
            }

    }
}
