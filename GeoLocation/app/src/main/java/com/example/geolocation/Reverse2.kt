package com.example.geolocation

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Geocoder
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.core.app.ActivityCompat
import java.util.*

class Reverse2 : AppCompatActivity() {

    lateinit var lm:LocationManager
    lateinit var loc:Location
    lateinit var tv2:TextView
    lateinit var back3:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reverse2)

        tv2=findViewById(R.id.textView)
        back3=findViewById(R.id.back3)


        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION), 111)
        }

        lm=getSystemService(Context.LOCATION_SERVICE) as LocationManager
        //loc=lm.getLastKnownLocation(LocationManager.GPS_PROVIDER)!!

        var ll=object:LocationListener {
            override fun onLocationChanged(p0: Location) {
                reverseGeocode(p0)
            }
        }
        lm.requestLocationUpdates(LocationManager.GPS_PROVIDER,100,102.2f,ll)


    }

    private fun reverseGeocode(loc: Location?) {

        var gc=Geocoder(this, Locale.getDefault())
        var addresses=gc.getFromLocation(loc!!.latitude,loc.longitude,2)
        var address=addresses.get(0)
        tv2.setText("${address.getAddressLine(0)} \n ${address.locality}")

    }


}