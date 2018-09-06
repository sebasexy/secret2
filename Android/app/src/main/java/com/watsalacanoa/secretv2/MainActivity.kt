package com.watsalacanoa.secretv2

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.design.widget.Snackbar
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.*
import android.widget.*

class MainActivity : AppCompatActivity() {


    private class Content(context: Context): BaseAdapter(){

        private val mContext: Context

        init {
            mContext = context
        }

        override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
            val textView = TextView(mContext)
            textView.text = "Hola"
            return textView
        }

        override fun getItem(p0: Int): Any {
            return "Test String"
        }

        override fun getItemId(p0: Int): Long {
            return 0
        }

        override fun getCount(): Int {
            return 5
        }



    }

    override fun onCreate(savedInstanceState: Bundle?) {


        var elements_to_display: Int = 0
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar = findViewById<View>(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)
        supportActionBar?.setTitle("Tablon")

        val listView = findViewById<ListView>(R.id.main_listView)
        listView.adapter = Content(this)


        var btnAddElement = findViewById<View>(R.id.btnAddNewElement)
        btnAddElement.setOnClickListener{
            val mBuilder = AlertDialog.Builder(this@MainActivity)

            var mView = LayoutInflater.from(this).inflate(R.layout.dialog_new_comment, null)
            var mComment = mView.findViewById<EditText>(R.id.idTextNewComment)
            var btnShare = mView.findViewById<Button>(R.id.btnShare)
            var btnCancel = mView.findViewById<Button>(R.id.btnCancel)

            btnShare.setOnClickListener{
                if(!mComment.text.isEmpty()){
                    Toast.makeText(this, mComment.text, Toast.LENGTH_SHORT).show()
                }
            }
            mBuilder.setView(mView)

        }

    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        val id = item.itemId

        return if (id == R.id.action_settings) {
            true
        } else super.onOptionsItemSelected(item)
    }


}
