package com.example.mydetails

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.*
import java.util.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var etEmail:EditText=findViewById(R.id.editTextTextEmailAddress)
        etEmail.addTextChangedListener(object :TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                        if(!android.util.Patterns.EMAIL_ADDRESS.matcher(p0).matches()){
                                    etEmail.setError("Invalid Input")
                        }
            }

            override fun afterTextChanged(p0: Editable?) {

            }

        })
        var actvCity:AutoCompleteTextView=findViewById(R.id.autoCompleteTextView)
        var city= arrayOf("rajkot","Surat","mumbai","baroda","pune","hydrabad")
        var adapter = ArrayAdapter<String>(this,android.R.layout.simple_selectable_list_item,city)
        actvCity.setAdapter(adapter)

        var mactvSkill : MultiAutoCompleteTextView=findViewById(R.id.multiAutoCompleteTextView)
        var skills = arrayOf("web design","web development","seo","python","flutter")
        var skillAdapter  = ArrayAdapter<String>(this,android.R.layout.simple_selectable_list_item,skills)
        mactvSkill.setAdapter(skillAdapter)
        mactvSkill.setTokenizer(MultiAutoCompleteTextView.CommaTokenizer())

        var etDate:EditText = findViewById(R.id.editTextDate3)
        var c = Calendar.getInstance()

        etDate.setOnClickListener {
            DatePickerDialog(this,DatePickerDialog.OnDateSetListener{datePicker, year, month, date ->
                etDate.setText("$date/${month+1}/$year")
            },c.get(Calendar.YEAR),c.get(Calendar.MONTH),c.get(Calendar.DAY_OF_MONTH)).show()
        }
        var etTime:EditText=findViewById(R.id.editTextTime)
        TimePickerDialog(this,TimePickerDialog.OnTimeSetListener{timePicker, hours, minutes ->
            etTime.setText("$hours : $minutes")
        },c.get(Calendar.HOUR),c.get(Calendar.MINUTE),true).show()
    }
}