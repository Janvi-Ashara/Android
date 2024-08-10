package com.example.mytask

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast

class CltvActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cltv)



        var TextView: TextView =findViewById(R.id.textView4)

//        var  tv2 = findViewById<TextView>(R.id.textView4)
//        TextView.setOnClickListener {
//            Toast.makeText(applicationContext, "welcome", Toast.LENGTH_SHORT).show()
//        }

        TextView.setOnClickListener {
            Toast.makeText(applicationContext, TextView.text, Toast.LENGTH_SHORT).show()
        }
    }
}