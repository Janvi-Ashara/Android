package com.example.audioprogram

import android.media.MediaPlayer
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    lateinit var  btnaudio: Button
    lateinit var btnstop : Button
    lateinit var btnaudio1:Button
    lateinit var btnstop1:Button
    lateinit var mp:MediaPlayer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnaudio=findViewById(R.id.btn1)
        btnstop=findViewById(R.id.btn2)
        btnaudio1=findViewById(R.id.button)
        btnstop1=findViewById(R.id.button2)

        btnaudio.setOnClickListener {
            mp=MediaPlayer.create(this,R.raw.ramsiyaram)
            mp.start()
        }
        btnstop.setOnClickListener {
            mp.stop()
        }

        //online
        btnaudio1.setOnClickListener {
            mp= MediaPlayer()
            mp.setDataSource(this, Uri.parse("https://pagallworld.co.in/wp-content/uploads/2023/10/O-My-Friend-Ganesha.mp3"))
            mp.prepare()
            mp.start()
        }
        btnstop1.setOnClickListener {
            mp.stop()
        }
    }
}