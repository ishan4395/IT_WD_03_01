package com.example.solarapp.cost
import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.solarapp.R
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
//import kotlinx.android.synthetic.main.activity_vacancy_scroll.*

class Add : AppCompatActivity() {

    private lateinit var EditText36: EditText
    private lateinit var EditText50: EditText

    private lateinit var button5: Button

    private lateinit var dbRef: DatabaseReference


    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add)

        EditText36 = findViewById(R.id.EditText36)
        EditText50 = findViewById(R.id.editText50)

        button5 = findViewById(R.id.button5)

        dbRef = FirebaseDatabase.getInstance().getReference("Solar")

        button5.setOnClickListener {
            saveSolarData()
        }

    }

    private fun saveSolarData(){

        val noOfPanels = EditText36.text.toString()

        val totalAmount = EditText50.text.toString()

        if (noOfPanels.isEmpty()) {
            EditText36.error = "Please fill the field"
        }
        if (totalAmount.isEmpty()) {
            EditText50.error = "Please  fill the field"
       }

        val sid = dbRef.push().key!!

       val solar = SolarModel(noOfPanels,totalAmount)

        dbRef.child(sid).setValue(solar)
            .addOnCompleteListener{
               Toast.makeText(this , " added successfully",Toast.LENGTH_LONG).show()


                EditText36.text.clear()
                EditText50.text.clear()


            }.addOnFailureListener{err ->
                Toast.makeText(this,"Error ${err.message}", Toast.LENGTH_LONG).show()
            }
    }
}







