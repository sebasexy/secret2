package com.watsalacanoa.secretv2.adapters

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.watsalacanoa.secretv2.R


class Content(mContext: Context, listElements: ArrayList<String>)
    : ArrayAdapter<String>(mContext,0, listElements){

    private val inflater = mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val element = getItem(position)

        var listItem = convertView
        if(listItem == null)
            listItem = inflater.inflate(R.layout.post, parent, false)

        val contentText = listItem!!.findViewById<TextView>(R.id.content)
        contentText.text = element
        return listItem
    }
}
