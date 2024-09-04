package com.example.listviewexample

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var listview: ListView = findViewById(R.id.listview)
        var city = arrayOf("Rajkot", "jamnager", "surat", "Boroda", "ahmadabad")
        var adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, city)
        listview.adapter = adapter

        listview.setOnItemClickListener { adapterView, view, i, l ->
            var value = listview.getItemAtPosition(i).toString()

            when (value){
                "Rajkot"->{
                    var i = Intent(this,secondactivity::class.java)
                    startActivity(i)
                }
                "surat"->{
                    var i = Intent(this,thirdactivity::class.java)
                    startActivity(i)
                }
                "jamnager"->{
                    var i = Intent(this,jamnageractivity::class.java)
                    startActivity(i)
                }
                "Boroda"->{
                    var i = Intent(this,borodaactivity::class.java)
                    startActivity(i)
                }
                "ahmadabad"->{
                    var i = Intent(this,ahmadavadactivity::class.java)
                    startActivity(i)
                }

            }
        }
    }
}