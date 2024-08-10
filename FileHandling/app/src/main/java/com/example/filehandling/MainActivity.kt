package com.example.filehandling

import android.content.Context
import android.content.DialogInterface
import android.os.Binder
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import java.io.BufferedReader
import java.io.InputStreamReader

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var ed_text:EditText=findViewById(R.id.editTextTextPersonName)
        var btn_write:Button=findViewById(R.id.button)


        var builder  = AlertDialog.Builder(this)
        btn_write.setOnClickListener {
            var fop = openFileOutput("myfile",Context.MODE_PRIVATE)
            fop.write(ed_text.text.toString().toByteArray())
                builder.setTitle("File Handling")
                builder.setMessage("Your Data is Saved!!!")
            builder.setPositiveButton("OK",DialogInterface.OnClickListener { dialogInterface, i ->

            })
            builder.setNegativeButton("Cancel",DialogInterface.OnClickListener { dialogInterface, i ->

            })
            builder.setNeutralButton("close",DialogInterface.OnClickListener { dialogInterface, i ->

            })
            builder.show()
        }
        var btn_read:Button=findViewById(R.id.button2)
        btn_read.setOnClickListener {
            var line:String? = ""
            var fip = application.openFileInput("myfile")
            var br = BufferedReader(InputStreamReader(fip))
            while(line != null){
                line=br.readLine()
                if(line != null){
                    Toast.makeText(applicationContext,line,Toast.LENGTH_SHORT).show()
                    ed_text.append(line+"\n")
                }
            }
            builder.setPositiveButton("OK",DialogInterface.OnClickListener { dialogInterface, i ->

            })
            builder.setNegativeButton("Cancel",DialogInterface.OnClickListener { dialogInterface, i ->

            })
            builder.setNeutralButton("close",DialogInterface.OnClickListener { dialogInterface, i ->

            })
            builder.show()

        }

    }
}