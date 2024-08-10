package com.example.registerform

import android.app.DatePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.*
import java.util.*

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        var uName:TextView = findViewById(R.id.editTextTextPersonName)
        var etEmail: EditText =findViewById(R.id.editTextTextEmailAddress2)
        etEmail.addTextChangedListener(object : TextWatcher {
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

        var actvCity: AutoCompleteTextView =findViewById(R.id.autoCompleteTextView)
        var city= arrayOf("rajkot","Surat","mumbai","baroda","pune","hydrabad")
        var adapter = ArrayAdapter<String>(this,android.R.layout.simple_selectable_list_item,city)
        actvCity.setAdapter(adapter)

        var txt: TextView =findViewById(R.id.textView)
        var ch1: CheckBox =findViewById(R.id.checkBox)
        var ch2: CheckBox =findViewById(R.id.checkBox2)
        var ch3: CheckBox =findViewById(R.id.checkBox3)
        var str:String="" //store selected hobbies
        ch1.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked){
                str += "Coding ,"
            }
        }
        ch2.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked){
                str += "Dancing ,"
            }
        }
        ch3.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked){
                str += "Reading ,"
            }
        }


//        ch1.setOnClickListener{
//            str ="Coding : ${ch1.isChecked}\nDancing : ${ch2.isChecked}\nReading : ${ch3.isChecked}"
//        }
//        ch2.setOnClickListener{
//            str ="Coding : ${ch1.isChecked}\nDancing : ${ch2.isChecked}\nReading : ${ch3.isChecked}"
//        }
//        ch3.setOnClickListener{
//            str ="Coding : ${ch1.isChecked}\nDancing : ${ch2.isChecked}\nReading : ${ch3.isChecked}"
//        }
        var rq:RadioGroup=findViewById(R.id.radiogroup)

        var str1 :String=""
        rq.setOnCheckedChangeListener{
                radioGroup,i->
            var rb = findViewById<RadioButton>(i)
            if(rb!=null){
                str1=rb.text.toString()
            }
        }

        var etDate:EditText = findViewById(R.id.editTextDate)
        var c = Calendar.getInstance()

        etDate.setOnClickListener {
            DatePickerDialog(this, DatePickerDialog.OnDateSetListener{ datePicker, year, month, date ->
                etDate.setText("$date/${month+1}/$year")
            },c.get(Calendar.YEAR),c.get(Calendar.MONTH),c.get(Calendar.DAY_OF_MONTH)).show()

          var button:Button=findViewById(R.id.button)
          button.setOnClickListener {
             var i =Intent(this,ThirdActivity::class.java)
              i.putExtra("name",uName.text.toString())
              i.putExtra("email",etEmail.text.toString())
              i.putExtra("city",actvCity.text.toString())
             i.putExtra("hobbies",str)
             i.putExtra("gender",str1)
              i.putExtra("date",etDate.text.toString())
              startActivity(i)

          }
        }


    }
}