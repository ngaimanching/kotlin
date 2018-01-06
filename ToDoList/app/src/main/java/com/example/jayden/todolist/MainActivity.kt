package com.example.jayden.todolist

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ListView

class MainActivity : AppCompatActivity() {

    private var todoList: ListView? = null
    private var addButton: ImageButton? = null
    private var todoData: MutableList<String>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        init()
    }




    private fun init() {
        todoList = findViewById(R.id.todoList)
        addButton = findViewById(R.id.addButton)
        todoData = ArrayList<String>()

        addButton!!.setOnClickListener { addData() }
    }


    private fun addData() {
        val alert = AlertDialog.Builder(this)
        val edittext = EditText(this)
        alert.setTitle("Please Enter ToDo Item")
        alert.setMessage("ToDo item")

        alert.setView(edittext)
        alert.setPositiveButton("Add") {dialog, whichButton ->
            val value = edittext.text.toString()
            todoData!!.add(value)
            setToDoListAdapter(todoData)

        }

        alert.setNegativeButton("Cancel") {dialog, whichButton ->
            // cancel dialog
        }

        alert.show()
    }


    private fun setToDoListAdapter(todoData: List<String>?) {
        // set up adapter for listView
        val adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1)
        adapter.addAll(todoData)
        todoList!!.adapter = adapter
    }
}
