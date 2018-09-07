package com.watsalacanoa.secretv2

import android.content.Context
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.util.Log
import android.view.*
import android.widget.*

class MainActivity : AppCompatActivity() {

    private class Content(mContext: Context, listElements: ArrayList<String>)
        : ArrayAdapter<String>(mContext,0, listElements){

        private val inflater = mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

        override fun getView(position: Int, convertView: View?,parent: ViewGroup?): View {
            val element = getItem(position)

            var listItem = convertView
            if(listItem == null)
                listItem = inflater.inflate(R.layout.post, parent, false)

            val contentText = listItem!!.findViewById<TextView>(R.id.content)
            contentText.text = element
            return listItem
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {

        val elementsArray = ArrayList<String>()

        elementsArray.add("Holaa")
        elementsArray.add("Pikachu")

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar = findViewById<View>(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)
        supportActionBar?.title = "Tablon"

        val listView = findViewById<ListView>(R.id.main_listView)
        listView.adapter = Content(this, elementsArray)

        val btnAddElement = findViewById<View>(R.id.btnAddNewElement)
        btnAddElement.setOnClickListener{

            val mBuilder = AlertDialog.Builder(this)

            val mView = LayoutInflater.from(this).inflate(R.layout.dialog_new_comment, null)
            val mComment = mView.findViewById<EditText>(R.id.idTextNewComment)
            val btnShare = mView.findViewById<Button>(R.id.btnShare)

            mBuilder.setView(mView)

            val alert = mBuilder.create()
            val btnCancel = mView.findViewById<Button>(R.id.btnCancel)

            btnShare.setOnClickListener{
                if(!mComment.text.isEmpty()){
                    runOnUiThread {
                        elementsArray.add(mComment.text.toString())
                    }
                    alert.cancel()
                }
            }

            btnCancel.setOnClickListener {
                alert.cancel()
            }
            alert.show()
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
