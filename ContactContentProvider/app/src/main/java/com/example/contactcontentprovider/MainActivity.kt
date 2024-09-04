package com.example.contactcontentprovider

import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.widget.ListView
import android.widget.SimpleCursorAdapter
import androidx.core.app.ActivityCompat

class MainActivity : AppCompatActivity() {

    var cols = arrayOf(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,ContactsContract.CommonDataKinds.Phone.NUMBER,ContactsContract.CommonDataKinds.Phone._ID)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if(ActivityCompat.checkSelfPermission(this,android.Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.READ_CONTACTS),111)
        }else{
            readContacts()
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if(requestCode==111 && grantResults[0] ==PackageManager.PERMISSION_GRANTED){
            readContacts()
        }
    }

    private fun readContacts() {

       var rs=contentResolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,cols,null,null,ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME)
        var adapter =SimpleCursorAdapter(applicationContext,android.R.layout.simple_list_item_2,rs,cols,
            intArrayOf(android.R.id.text1,android.R.id.text2),0)
        var listview :ListView=findViewById(R.id.list)
        listview.adapter=adapter
    }

}