package com.example.contactapp

import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import android.database.Cursor
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.ListView
import android.widget.SearchView
import android.widget.SimpleCursorAdapter
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.material.floatingactionbutton.FloatingActionButton
import android.widget.AdapterView

class MainActivity : AppCompatActivity() {

    lateinit  var listview: ListView
    private val REQUEST_CODE_READ_CONTACTS_PERMISSION = 117
    private val REQUEST_CODE_CALL_PHONE_PERMISSION = 118

    var cols = arrayOf(
        ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,
        ContactsContract.CommonDataKinds.Phone.NUMBER,
        ContactsContract.CommonDataKinds.Phone._ID
    )

    private lateinit var adapter: SimpleCursorAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        if (ActivityCompat.checkSelfPermission(
                this,
                android.Manifest.permission.READ_CONTACTS
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(android.Manifest.permission.READ_CONTACTS),
                111
            )
        } else {
            readContacts()
        }

        var add: FloatingActionButton = findViewById(R.id.floatingActionButton3)
        add.setOnClickListener {
            var i = Intent(Intent.ACTION_INSERT).apply {
                type = ContactsContract.Contacts.CONTENT_TYPE
            }
            startActivity(i)
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 118 && grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            readContacts()
        }
    }

    @SuppressLint("Range")
    private fun readContacts() {

        var cursor = contentResolver.query(
            ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
            cols,
            null,
            null,
            null)
        cursor?.let {
            adapter =
                SimpleCursorAdapter(
                    applicationContext, R.layout.mylayout, cursor, cols,
                    intArrayOf(R.id.textview1, R.id.textview2), 0
                )
            listview= findViewById(R.id.list1)
            registerForContextMenu(listview)

            listview.adapter = adapter

            listview.setOnItemClickListener { parent, view, i, l ->
                val cursor = parent.getItemAtPosition(i) as android.database.Cursor
                val phoneNumber =
                    cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER))
                makeCall(phoneNumber)
            }

            val Search: SearchView = findViewById(R.id.search_view)

            Search.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    filterContacts(query)
                    return false
                }


                override fun onQueryTextChange(newText: String?): Boolean {
                    filterContacts(newText)
                    return true
                }
            })
        }
    }

    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?

    ) {

        menu?.add(101,1111,1,"Call")
        menu?.add(102,1112,2,"Message")
        menu?.add(103,1113,3,"Whatsapp")

        super.onCreateContextMenu(menu, v, menuInfo)
    }

    @SuppressLint("Range")
    override fun onContextItemSelected(item: MenuItem): Boolean {
        val info = item.menuInfo as AdapterView.AdapterContextMenuInfo
        val cursor = adapter.getItem(info.position) as Cursor
        val phoneNumber = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER))

        return when (item.itemId) {
            1111 -> {
                makeCall(phoneNumber)
                true
            }
            1112 -> {
                sendMessage(phoneNumber)
                true
            }
            1113 -> {
                openWhatsapp(phoneNumber)
                true
            }
            else -> super.onContextItemSelected(item)
        }

        return super.onContextItemSelected(item)
    }

    private fun makeCall(phoneNumber: String?) {
                if (ContextCompat.checkSelfPermission(this,android.Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
                    val i = Intent(Intent.ACTION_CALL).apply {
                        data = Uri.parse("tel:$phoneNumber")
                    }
                    startActivity(i)
                } else{
                        ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.CALL_PHONE),REQUEST_CODE_CALL_PHONE_PERMISSION)
                    }
                }

    private fun sendMessage(phoneNumber: String?) {
        val intent = Intent(Intent.ACTION_VIEW).apply {
            data = Uri.parse("smsto:$phoneNumber")
        }
        startActivity(intent)
    }

    private fun openWhatsapp(phoneNumber: String?) {
        val intent = Intent(Intent.ACTION_VIEW).apply {
            data = Uri.parse("whatsapp://send?phone=$phoneNumber")
        }
        if (intent.resolveActivity(packageManager) != null) {
            startActivity(intent)
        } else {
            Toast.makeText(applicationContext, "whatsapp is not installed", Toast.LENGTH_SHORT).show()
        }
    }


    @SuppressLint("Range")
    private fun filterContacts(query: String?) {
        val filterCursor = contentResolver.query(
            ContactsContract.CommonDataKinds.Phone.CONTENT_URI, cols,
            "${ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME} LIKE ?",
            arrayOf("%${query ?: ""}%"),
            null
        )
        adapter.changeCursor(filterCursor)
    }
}







