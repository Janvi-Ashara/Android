package com.example.city_task

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton

class PuneActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pune)
        var btn: ImageButton =findViewById(R.id.imageButton)
        btn.setOnClickListener {
            var i= Intent(this,MainActivity::class.java)
            startActivity(i)
        }
    }
}