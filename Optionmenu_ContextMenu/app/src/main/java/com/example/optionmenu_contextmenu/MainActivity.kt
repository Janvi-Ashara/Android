package com.example.optionmenu_contextmenu

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ContextMenu
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.AutoCompleteTextView
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    lateinit var textView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textView = findViewById(R.id.text)
        registerForContextMenu(textView)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        menu?.add(101, 1001, 1, "New Group")
        menu?.add(102, 1002, 2, "New Broadcast")
        menu?.add(103, 1003, 3, "New Linked Device")
        menu?.add(104, 1004, 4, "Payment")
        menu?.add(105, 1005, 5, "Setting")

        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            1001 -> Toast.makeText(applicationContext, "new Group selected", Toast.LENGTH_SHORT)
                .show()
            1002 -> Toast.makeText(applicationContext, "new Broadcast clicked", Toast.LENGTH_SHORT)
                .show()
            1003 -> textView.textSize = 40f

            1005 -> {
                var i = Intent(this, SettindActivity::class.java)
                startActivity(i)
            }

        }
            return super.onOptionsItemSelected(item)

    }

    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        menu?.setHeaderTitle("Choose Color")
        menu?.add(101,1001,1,"RED")
        menu?.add(102,1002,2,"BLUE")
        menu?.add(103,1003,3,"YELLOW")
        menu?.add(104,1004,4,"CYAN")
        menu?.add(105,1005,5,"GREEN")

        super.onCreateContextMenu(menu, v, menuInfo)
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            1001->textView.setTextColor(Color.RED)
            1002->textView.setTextColor(Color.BLUE)
            1003->textView.setTextColor(Color.YELLOW)
            1004->textView.setTextColor(Color.CYAN)
            1005->textView.setTextColor(Color.GREEN)
                    }
        return super.onContextItemSelected(item)
    }
}