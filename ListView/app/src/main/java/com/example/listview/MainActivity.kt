package com.example.listview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var listView:ListView=findViewById(R.id.ListView)
        var city= arrayOf("mumbai","pune","baroda","ahmedabad","bangaluru")
        var adapter=ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,city)
        listView.adapter=adapter
        listView.setOnItemClickListener { adapterView, view, i, l ->
            var value=listView.getItemAtPosition(i).toString()
            Toast.makeText(applicationContext,value,Toast.LENGTH_SHORT).show()
        }
    }
}