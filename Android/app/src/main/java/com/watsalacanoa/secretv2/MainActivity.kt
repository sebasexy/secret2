package com.watsalacanoa.secretv2

import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.util.Log
import android.view.*
import android.widget.*

import com.watsalacanoa.secretv2.adapters.Content

class MainActivity : AppCompatActivity() {

    private val elementsArray = ArrayList<String>()
    private lateinit var adapter : Content

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        adapter = Content(this, elementsArray)

        setContentView(R.layout.activity_main)
        val toolbar = findViewById<View>(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)
        supportActionBar?.title = "Tablon"

        val listView = findViewById<ListView>(R.id.main_listView)
        listView.adapter = adapter

        val btnAddElement = findViewById<View>(R.id.btnAddNewElement)
        btnAddElement.setOnClickListener{v -> showDialog(v)}
    }

    fun showDialog(view:View){
        val mBuilder = AlertDialog.Builder(this)

        val mView = LayoutInflater.from(this).inflate(R.layout.dialog_new_comment, null)
        val mComment = mView.findViewById<EditText>(R.id.idTextNewComment)
        val btnShare = mView.findViewById<Button>(R.id.btnShare)

        mBuilder.setView(mView)

        val alert = mBuilder.create()
        val btnCancel = mView.findViewById<Button>(R.id.btnCancel)

        btnShare.setOnClickListener{
            val txt = mComment.text.toString()
            if(!txt.isEmpty()){
                runOnUiThread {
                    adapter.add(txt)
                }
            }
            alert.cancel()
        }

        btnCancel.setOnClickListener {
            alert.cancel()
        }
        alert.show()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId

        return if (id == R.id.action_settings) {
            true
        } else super.onOptionsItemSelected(item)
    }


}
