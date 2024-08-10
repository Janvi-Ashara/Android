package com.example.typesofintent

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class
SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        var txtSurname: TextView=findViewById(R.id.textView2)
        var txtName:TextView=findViewById(R.id.textView3)

        var intent:Intent=intent
        var sName=intent.getStringExtra("Surname")
        var Name =intent.getStringExtra("name")

        txtSurname.setText("Surname :" +sName)
        txtName.setText("Name :" +Name)

        var btn_web:Button=findViewById(R.id.button2)

        btn_web.setOnClickListener {
            var uri = Uri.parse("https://www.google.com")
            var i= Intent(Intent.ACTION_VIEW,uri)
            startActivity(i)
        }

        var ed_number : EditText=findViewById(R.id.editTextTextPersonName3)
        var btn_phone : Button = findViewById(R.id.button3)

        btn_phone.setOnClickListener {
            var uri = Uri.parse("tel:"+ed_number.text.toString())
            var i =Intent(Intent.ACTION_DIAL,uri)
            startActivity(i)
        }
        var btn_location : Button=findViewById(R.id.button4)
        btn_location.setOnClickListener {
            var uri = Uri.parse("geo:0,0=?"+"Atmiya university, Rajkot")
            var i=Intent(Intent.ACTION_VIEW,uri)
            startActivity(i)
        }
    }
}