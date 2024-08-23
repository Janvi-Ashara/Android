package com.example.sharedprefernce

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var ed_unm: EditText = findViewById(R.id.editText)
        var ed_pass: EditText = findViewById(R.id.editText1)
        var btn_save: Button = findViewById(R.id.button)
        var btn_view:Button=findViewById(R.id.button1)
        var sp = application.getSharedPreferences("loginfile", Context.MODE_PRIVATE)
        var editor = sp.edit()

        btn_save.setOnClickListener {
            editor.putString("unm", ed_unm.text.toString())
            editor.putString("pass", ed_pass.text.toString())
            editor.commit()
            Toast.makeText(applicationContext, "Data saved", Toast.LENGTH_SHORT).show()
            ed_unm.setText("")
            ed_pass.setText("")
        }
    btn_view.setOnClickListener {
        ed_unm.setText((sp.getString("unm","")))
        ed_pass.setText((sp.getString("pass","")))
    }

    }
}