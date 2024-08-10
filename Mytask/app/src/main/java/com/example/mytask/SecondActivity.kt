package com.example.mytask

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

            var btn1:ImageButton=findViewById(R.id.imageButton)
            btn1.setOnClickListener {
            var i=Intent(this,CltvActivity::class.java)
            startActivity(i)
        }
        var btn2:ImageButton=findViewById(R.id.imageButton2)
        btn2.setOnClickListener {
            var i=Intent(this,LletActivity::class.java)
            startActivity(i)
        }
        var btn3:ImageButton=findViewById(R.id.imageButton3)
        btn3.setOnClickListener {
            var i=Intent(this,RlbtnActivity::class.java)
            startActivity(i)
        }
        var btn4:ImageButton=findViewById(R.id.imageButton4)
        btn4.setOnClickListener {
            var i=Intent(this,FlpbsbActivity::class.java)
            startActivity(i)
        }
    }
}