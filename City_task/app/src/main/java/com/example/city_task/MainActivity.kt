package com.example.city_task

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var listview:ListView=findViewById(R.id.list_view)
        var city= arrayOf("jaipur","mumbai","pune","bangalore","banaras")
        var adapter=ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,city)
        listview.adapter=adapter

        listview.setOnItemClickListener { adapterView, view, i, l ->
            var value=listview.getItemAtPosition(i).toString()
           if(value=="jaipur"){
                var i=Intent(this,JaipurActivity::class.java)
                startActivity(i)
            }
            else if(value=="mumbai"){
                var i =Intent(this,MumbaiActivity::class.java)
               startActivity(i)
           }
           else if(value=="pune"){
               var i =Intent(this,PuneActivity::class.java)
               startActivity(i)
           }
           else if(value=="bangalore"){
               var i =Intent(this,BangaloreActivity::class.java)
               startActivity(i)
           }
           else if(value=="banaras"){
               var i =Intent(this,BanarasActivity::class.java)
               startActivity(i)
           }
        }

    }
}