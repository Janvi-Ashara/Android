package com.example.mytask

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import com.google.android.material.floatingactionbutton.FloatingActionButton

class RlbtnActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rlbtn)
        var btn: Button = findViewById(R.id.button)
        var imgbtn: ImageButton =findViewById(R.id.imageButton3)
        var tglbtn: ToggleButton =findViewById(R.id.toggleButton)
        var imgView : ImageView =findViewById(R.id.imageView3)
        var fab: FloatingActionButton =findViewById(R.id.flb12)
        var edt: FloatingActionButton =findViewById(R.id.flb3)

        btn.setOnClickListener {
            Toast.makeText(applicationContext, "simple button clicked", Toast.LENGTH_SHORT).show()
        }
        imgbtn.setOnClickListener {
            Toast.makeText(applicationContext, "image clicked!!!!", Toast.LENGTH_SHORT).show()
        }
        tglbtn.setOnClickListener {
            if(tglbtn.text.equals("OFF")){
                imgView.setImageResource(R.drawable.off)
            }
            else{
                imgView.setImageResource(R.drawable.on)
            }
        }
        fab.setOnClickListener {
            Toast.makeText(applicationContext, "floating action button clicked", Toast.LENGTH_SHORT).show()
        }
        edt.setOnClickListener {
            Toast.makeText(applicationContext, "floating action call clicked", Toast.LENGTH_SHORT)
                .show()
        }
        var txt: TextView =findViewById(R.id.textView)
        var ch1: CheckBox =findViewById(R.id.checkBox)
        var ch2: CheckBox =findViewById(R.id.checkBox2)
        var ch3: CheckBox =findViewById(R.id.checkBox3)
        var str:String
        ch1.setOnClickListener{
            str ="Java : ${ch1.isChecked}\nKotlin : ${ch2.isChecked}\nAndroid : ${ch3.isChecked}"
            txt.setText(str)

        }
        ch2.setOnClickListener{
            str ="Java : ${ch1.isChecked}\nKotlin : ${ch2.isChecked}\nAndroid : ${ch3.isChecked}"
            txt.setText(str)

        }
        ch3.setOnClickListener{
            str ="Java : ${ch1.isChecked}\nKotlin : ${ch2.isChecked}\nAndroid : ${ch3.isChecked}"
            txt.setText(str)

        }

        var rq: RadioGroup =findViewById(R.id.radiogroup)
        var tv2: TextView =findViewById(R.id.textView2)
        var resetbtn : Button =findViewById(R.id.button2)

        rq.setOnCheckedChangeListener{
                radioGroup,i->
            var rb = findViewById<RadioButton>(i)
            if(rb!=null){
                tv2.setText(rb.text)
            }
        }
        resetbtn.setOnClickListener {
            rq.clearCheck()
            tv2.setText("select one")
        }
    }
}