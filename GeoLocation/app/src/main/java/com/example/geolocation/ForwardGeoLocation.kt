package com.example.geolocation

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Geocoder
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.core.app.ActivityCompat
import java.util.*

class ForwardGeoLocation : AppCompatActivity() {

    lateinit var ed: EditText
    lateinit var bu: Button
    lateinit var tv: TextView
    lateinit var back1:Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forward_geo_location2)


        ed=findViewById(R.id.editTextTextPersonName)
        bu=findViewById(R.id.button)
        tv=findViewById(R.id.textView)
        back1=findViewById(R.id.back1)



        if (ActivityCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)!= PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(this, arrayOf(
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION),111)
        }
        else
        {
            bu.setOnClickListener {
                var city=ed.text.toString()
                forwardGeoLocation(city)
            }
        }

        back1.setOnClickListener {
            startActivity(Intent(this,MainActivity::class.java))
        }


    }


    private fun forwardGeoLocation(city: String) {
        var gc= Geocoder(this, Locale.getDefault())
        var addresses = gc.getFromLocationName(city,2)
        var address=addresses.get(0)

        tv.setText("Latitude:==  ${address.latitude} \n Longitude:==  ${address.longitude} \n City:==   ${address.locality}")
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if(requestCode==111 && grantResults[0]==PackageManager.PERMISSION_GRANTED)
        {
            var city=ed.text.toString()

            forwardGeoLocation(city)
        }
    }
}