package com.example.task3

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*

class DisplayForm : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_display_form)

        var txtsurname: EditText =findViewById(R.id.editTextTextPersonName)
        var txtName: EditText =findViewById(R.id.editTextText_PersonName)
        var txtpass:EditText=findViewById(R.id.editTextTextPassword3)
        var txtEmail: EditText =findViewById(R.id.editTextTextEmailAddress2)
        var txtcity: EditText =findViewById(R.id.autoCompleteTextView)
        var txtgender :RadioGroup =findViewById(R.id.radiogroup)
        var txtdate: EditText =findViewById(R.id.editTextDate)
        var btn_edit:Button=findViewById(R.id.button)

        var sp = application.getSharedPreferences("Registerfile", Context.MODE_PRIVATE)
        var editor = sp.edit()

        txtsurname.setText(sp.getString("snm",""))
        txtName.setText(sp.getString("name",""))
        txtpass.setText(sp.getString("pass",""))
        txtEmail.setText(sp.getString("email",""))
        txtcity.setText(sp.getString("city",""))
        txtdate.setText(sp.getString("date",""))

        btn_edit.setOnClickListener {
            editor.putString("snm", txtsurname.text.toString())
            editor.putString("name", txtName.text.toString())
            editor.putString("pass",txtpass.text.toString())
            editor.putString("email",txtEmail.text.toString())
            editor.putString("city",txtcity.text.toString())
            //editor.putString("gender",str1)
            editor.putString("date",txtdate.text.toString())
            editor.commit()
        }

    }
}