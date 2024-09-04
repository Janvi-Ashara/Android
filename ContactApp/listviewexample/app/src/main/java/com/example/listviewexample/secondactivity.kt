package com.example.listviewexample

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class secondactivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_secondactivity)


        var btn:Button=findViewById(R.id.button)
         btn.setOnClickListener {
             var i=Intent(this,MainActivity::class.java)
             startActivity(i)
         }
    }
}