package com.example.jayden.kotlin_toast

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val button = findViewById(R.id.button) as Button

        button.setOnClickListener {

            showToast("Hello kotlin 2", Toast.LENGTH_LONG)
        }

    }

    // function toast
    fun showToast(message:String, length:Int = Toast.LENGTH_SHORT) {
        Toast.makeText(this, message, length).show()
    }
}
