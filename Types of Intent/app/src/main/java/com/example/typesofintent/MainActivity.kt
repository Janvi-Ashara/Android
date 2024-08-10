package com.example.typesofintent

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var btn:Button=findViewById(R.id.button)
        var edsurname : EditText=findViewById(R.id.editTextTextPersonName)
        var edname:EditText=findViewById(R.id.editTextTextPersonName2)

        btn.setOnClickListener{
            var i =Intent(this,SecondActivity::class.java)
            i.putExtra("Surname",edsurname.text.toString())
            i.putExtra("name",edname.text.toString())
            startActivity(i)

        }

    }
}