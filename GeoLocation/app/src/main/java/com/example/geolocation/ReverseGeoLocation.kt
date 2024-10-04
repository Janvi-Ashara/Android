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

class ReverseGeoLocation : AppCompatActivity() {


     lateinit var edLatitude: EditText
     lateinit var edLongitude: EditText
     lateinit var buReverse: Button
     lateinit var tv: TextView
     lateinit var back2:Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reverse_geo_location)


        edLatitude = findViewById(R.id.editTextLatitude)
        edLongitude = findViewById(R.id.editTextLongitude)

        buReverse = findViewById(R.id.buttonReverse)
        tv = findViewById(R.id.textView)

        back2=findViewById(R.id.back2)

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION), 111)
        } else {
            setupButtonClickListeners()
        }

        back2.setOnClickListener {
            startActivity(Intent(this,MainActivity::class.java))
        }

    }
    private fun setupButtonClickListeners() {


        buReverse.setOnClickListener {
            val latitudeStr = edLatitude.text.toString()
            val longitudeStr = edLongitude.text.toString()
            if (latitudeStr.isNotEmpty() && longitudeStr.isNotEmpty()) {
                try {
                    val latitude = latitudeStr.toDouble()
                    val longitude = longitudeStr.toDouble()
                    reverseGeoLocation(latitude, longitude)
                } catch (e: NumberFormatException) {
                    tv.text = "Invalid latitude or longitude."
                }
            } else {
                tv.text = "Please enter both latitude and longitude."
            }
        }
    }



    private fun reverseGeoLocation(latitude: Double, longitude: Double) {
        val geocoder = Geocoder(this, Locale.getDefault())
        try {
            val addresses = geocoder.getFromLocation(latitude, longitude, 1)
            if (addresses.isNotEmpty()) {
                val address = addresses[0]
                val addressText = buildString {
                    append("Address: ${address.getAddressLine(0)}\n")
                    append("City: ${address.locality}\n")
                    append("State: ${address.adminArea}\n")
                    append("Country: ${address.countryName}")
                }
                tv.text = addressText
            } else {
                tv.text = "No address found for the given coordinates."
            }
        } catch (e: Exception) {
            e.printStackTrace()
            tv.text = "Geocoding failed. Please try again."
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 111) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                setupButtonClickListeners()
            } else {
                tv.text = "Location permission is required for this feature."
            }
        }
    }
}