package com.example.geolocation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var fwgeoloc:Button=findViewById(R.id.fwgeoloc)
        var rvgeoloc:Button=findViewById(R.id.rvgeoloc)
        var currentgeoloc:Button=findViewById(R.id.currentgeoloc)
        var exit:Button=findViewById(R.id.exit)

        fwgeoloc.setOnClickListener {
            var i=Intent(this,ForwardGeoLocation::class.java)
            startActivity(i)
        }

        rvgeoloc.setOnClickListener {
            var i=Intent(this,ReverseGeoLocation::class.java)
            startActivity(i)
        }

        currentgeoloc.setOnClickListener {
            var i=Intent(this,Reverse2::class.java)
            startActivity(i)
        }

        exit.setOnClickListener {
            System.exit(0)
        }




    }
}