package com.example.jayden.recycleview

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recycleView = findViewById(R.id.recycleView) as RecyclerView

        val adapter = CustomAdapter(this)
        recycleView.layoutManager = GridLayoutManager(this, 2)
        recycleView.adapter = adapter

    }
}
