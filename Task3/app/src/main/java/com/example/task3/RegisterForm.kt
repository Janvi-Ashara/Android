package com.example.task3

import android.app.DatePickerDialog
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.*
import java.util.*

class RegisterForm : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_form)

        var sName: TextView = findViewById(R.id.editTextTextPersonName)
        var Name : TextView=findViewById(R.id.editTextText_PersonName)
        var Pass : TextView=findViewById(R.id.editTextTextPassword3)
        var etEmail: EditText =findViewById(R.id.editTextTextEmailAddress2)
        var sp = application.getSharedPreferences("Registerfile", Context.MODE_PRIVATE)
        var editor = sp.edit()

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

        var rq: RadioGroup =findViewById(R.id.radiogroup)

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
            DatePickerDialog(
                this,
                DatePickerDialog.OnDateSetListener { datePicker, year, month, date ->
                    etDate.setText("$date/${month + 1}/$year")
                },
                c.get(Calendar.YEAR),
                c.get(Calendar.MONTH),
                c.get(Calendar.DAY_OF_MONTH)
            ).show()
        }

                var button:Button=findViewById(R.id.button)
                button.setOnClickListener {
                    var i = Intent(this,DisplayForm::class.java)
                    editor.putString("snm", sName.text.toString())
                    editor.putString("name", Name.text.toString())
                    editor.putString("pass",Pass.text.toString())
                    editor.putString("email",etEmail.text.toString())
                    editor.putString("city",actvCity.text.toString())
                    editor.putString("gender",str1)
                    editor.putString("date",etDate.text.toString())
                    editor.commit()
                    Toast.makeText(applicationContext, "Data saved", Toast.LENGTH_SHORT).show()
                    sName.setText("")
                    Name.setText("")
                    Pass.setText("")
                    etEmail.setText("")
                    actvCity.setText("")
                    str1
                    etDate.setText("")
                    startActivity(i)
                }
            }

        }


