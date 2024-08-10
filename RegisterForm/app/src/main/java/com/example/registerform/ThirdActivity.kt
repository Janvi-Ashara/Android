package com.example.registerform

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class ThirdActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_third)

        var txtName:TextView=findViewById(R.id.textView4)
        var txtEmail:TextView=findViewById(R.id.textView5)
        var txtcity:TextView=findViewById(R.id.textView6)
        var txthobby:TextView=findViewById(R.id.textView7)
        var txtgender:TextView=findViewById(R.id.textView8)
        var txtdate:TextView=findViewById(R.id.textView9)

        var intent:Intent=intent

        var dName=intent.getStringExtra("name")
        txtName.setText("Name :"+dName)

        var dEmail=intent.getStringExtra("email")
        txtEmail.setText("Eamil :"+dEmail)

        var dCity=intent.getStringExtra("city")
        txtcity.setText("City :"+dCity)

        var dhobby=intent.getStringExtra("hobbies")
        txthobby.setText("hobby :"+dhobby)

        var dgender=intent.getStringExtra("gender")
        txtgender.setText("Gender :"+dgender)

        var dDate=intent.getStringExtra("date")
        txtdate.setText("Date :"+dDate)

    }
}