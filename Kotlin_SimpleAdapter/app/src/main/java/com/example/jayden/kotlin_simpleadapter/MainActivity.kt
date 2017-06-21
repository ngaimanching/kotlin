package com.example.jayden.kotlin_simpleadapter

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView
import android.widget.SimpleAdapter

class MainActivity : AppCompatActivity() {

    // title array
    private val listTitle = arrayOf("Android", "Version", "Factory", "Language")
    // detail array
    private val listDetails = arrayOf("Android O", "7.x", "Samsung", "Kotlin")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val data = ArrayList<HashMap<String, Any>>()

        val lengh = listTitle.size

        for (i in 0..lengh -1) {
            val item = HashMap<String, Any>()

            item.put("title", listTitle[i])

            item.put("detail", listDetails[i])

            data.add(item)
        }

        val listview = findViewById(R.id.listview) as ListView

        val adapter = SimpleAdapter(this, data, android.R.layout.simple_list_item_2,
                                    arrayOf("title", "detail"), intArrayOf(android.R.id.text1, android.R.id.text2))

        listview.adapter = adapter



    }
}
