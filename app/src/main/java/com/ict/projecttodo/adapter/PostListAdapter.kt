package com.ict.projecttodo.adapter

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.TextView
import com.google.android.material.snackbar.Snackbar
import com.ict.projecttodo.R
import com.ict.projecttodo.models.Post

class PostListAdapter(context: Context, private val listOfPosts: List<Post>) :
    ArrayAdapter<Post>(context, 0, listOfPosts) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        val post = getItem(position)


        val view = convertView ?: LayoutInflater.from(context).inflate(
            R.layout.todo_item, parent, false
        )


        val title = view.findViewById<TextView>(R.id.titleTextView)
        val id= view.findViewById<TextView>(R.id.tvid)
        val userId= view.findViewById<TextView>(R.id.tvUserid)
        val completed=view.findViewById<TextView>(R.id.completedTextView)



        title.text=listOfPosts[position].title
        id.text=listOfPosts[position].id.toString()
        userId.text=listOfPosts[position].userId.toString()
        completed.text=listOfPosts[position].completed.toString()

        if (listOfPosts[position].completed) {
            view.setBackgroundColor(Color.parseColor("#00FF00"))
        } else {
            view.setBackgroundColor(Color.parseColor("#ba160c"))

        }



        return view
    }
}