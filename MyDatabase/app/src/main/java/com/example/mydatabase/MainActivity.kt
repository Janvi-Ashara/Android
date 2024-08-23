package com.example.mydatabase

import android.content.ContentValues
import android.content.DialogInterface
import android.database.Cursor
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AlertDialog

class MainActivity : AppCompatActivity() {
    
    lateinit var ed_sname : EditText
    lateinit var ed_sem : EditText
    lateinit var btn_insert:Button
    lateinit var btn_update : Button
    lateinit var btn_delete : Button
    lateinit var btn_clear :Button
    lateinit var btn_next : Button
    lateinit var btn_prev : Button
    lateinit var btn_first : Button
    lateinit var btn_last: Button
    lateinit var btn_showall : Button
    lateinit var list_View : ListView
    lateinit var search : SearchView
    lateinit var rs : Cursor
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        ed_sname = findViewById(R.id.text1)
        ed_sem = findViewById(R.id.text2)
        btn_insert = findViewById(R.id.button)
        btn_update = findViewById(R.id.button2)
        btn_delete = findViewById(R.id.button3)
        btn_clear = findViewById(R.id.button4)
        btn_next = findViewById(R.id.button5)
        btn_prev = findViewById(R.id.button6)
        btn_first = findViewById(R.id.button7)
        btn_last = findViewById(R.id.button8)
        btn_showall=findViewById(R.id.button9)
        list_View=findViewById(R.id.listView)
        search = findViewById(R.id.search_view)

        var helper = MyDBHelper(applicationContext)
        var db = helper.writableDatabase
        Toast.makeText(applicationContext, "Db CREATED", Toast.LENGTH_SHORT).show()
        rs = db.rawQuery("SELECT SID _id,SNAME, SEM FROM STUDENT", null)
        if (rs.moveToFirst()) {
            ed_sname.setText(rs.getString(1))
            ed_sem.setText(rs.getString(2))
        }
        btn_insert.setOnClickListener {
            var cv = ContentValues()
            cv.put("SNAME", ed_sname.text.toString())
            cv.put("SEM", ed_sem.text.toString())
            db.insert("STUDENT", null, cv)
            rs = db.rawQuery("SELECT SID _id, SNAME,SEM FROM STUDENT", null)
            showMessage("Record inserted successfully")
            clear()
        }
        btn_update.setOnClickListener {
            var cv = ContentValues()
            cv.put("SNAME", ed_sname.text.toString())
            cv.put("SEM", ed_sem.text.toString())
            db.update("STUDENT", cv, "SID = ?", arrayOf(rs.getString(0)))
            rs = db.rawQuery("SELECT SID _id, SNAME,SEM FROM STUDENT", null)
            showMessage("Record update successfully")
            clear()
        }
        btn_delete.setOnClickListener {
            db.delete("STUDENT", "SID = ?", arrayOf(rs.getString(0)))
            rs = db.rawQuery("SELECT SID _id, SNAME,SEM FROM STUDENT", null)
            showMessage("Record update successfully")
            clear()
        }

        btn_clear.setOnClickListener {
            clear()
        }
        btn_prev.setOnClickListener {
            if (rs.moveToPrevious()) {
                ed_sname.setText(rs.getString(1))
                ed_sem.setText(rs.getString(2))
            } else if (rs.moveToLast()) {
                ed_sname.setText(rs.getString(1))
                ed_sem.setText(rs.getString(2))
            } else {
                Toast.makeText(applicationContext, "Data not found !!", Toast.LENGTH_SHORT).show()
            }
        }
        btn_next.setOnClickListener {
            if (rs.moveToNext()) {
                ed_sname.setText(rs.getString(1))
                ed_sem.setText(rs.getString(2))
            } else if (rs.moveToFirst()) {
                ed_sname.setText(rs.getString(1))
                ed_sem.setText(rs.getString(2))
            } else {
                Toast.makeText(applicationContext, "Data not found", Toast.LENGTH_SHORT).show()
            }
        }
            btn_first.setOnClickListener {
                if (rs.moveToFirst()) {
                    ed_sname.setText(rs.getString(1))
                    ed_sem.setText(rs.getString(2))
                } else {
                    Toast.makeText(applicationContext, "Data not found..", Toast.LENGTH_SHORT)
                        .show()
                }
            }
            btn_last.setOnClickListener {
                if (rs.moveToLast()) {
                    ed_sname.setText(rs.getString(1))
                    ed_sem.setText(rs.getString(2))
                } else {
                    Toast.makeText(applicationContext, "Data not found", Toast.LENGTH_SHORT).show()
                }
            }
        btn_showall.setOnClickListener {

            search.queryHint="Search Among ${rs.count} Records"
            var adapter = SimpleCursorAdapter(applicationContext,R.layout.my_layout,rs,
            arrayOf("SNAME","SEM"),
            intArrayOf(R.id.textview1,R.id.textview2))
            list_View.adapter=adapter

            search.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
                override fun onQueryTextSubmit(p0: String?): Boolean {
                    return false
                }

                override fun onQueryTextChange(p0: String?): Boolean {
                    rs=db.rawQuery("SELECT SID _id,SNAME,SEM FROM STUDENT WHERE SNAME LIKE '%${p0}%'",null)
                    adapter.changeCursor(rs)
                   return false
                }

            })
        }

        }

        private fun showMessage(s: String) {
            AlertDialog.Builder(this)
                .setTitle("Success...")
                .setMessage(s)
                .setPositiveButton("ok", DialogInterface.OnClickListener { dialogInterface, i ->
                    if (rs.moveToFirst()) {
                        ed_sname.setText(rs.getString(1))
                        ed_sem.setText(rs.getString(2))
                    } else {
                        Toast.makeText(
                            applicationContext,
                            "DB and Table created",
                            Toast.LENGTH_SHORT
                        ).show()

                    }
                }).show()

        }
        private fun clear(){
            ed_sname.setText("")
            ed_sem.setText("")
            ed_sname.requestFocus()

    }
}
