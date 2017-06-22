package com.example.jayden.kotlin_httpget

import android.os.AsyncTask
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import android.widget.TextView

import java.io.BufferedInputStream
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val httpGET = findViewById(R.id.button) as Button
        val textResult = findViewById(R.id.tv_result) as TextView

        httpGET.setOnClickListener {
            HttpTask(textResult).execute()
        }


    }


    class HttpTask(textView: TextView) : AsyncTask<Unit, Unit, String>() {

        val innerTextView: TextView? = textView

        override fun doInBackground(vararg params: Unit?): String? {
            val url = URL("http://10.250.163.66:8888/kotlin.txt")   // Your localhost address or network address
            val httpClient = url.openConnection() as HttpURLConnection
            if (httpClient.responseCode == HttpURLConnection.HTTP_OK) {
                try {
                    val stream = BufferedInputStream(httpClient.inputStream)
                    val data: String = readStream(inputStream = stream)
                    return data
                } catch (e: Exception) {
                    e.printStackTrace()
                } finally {
                    httpClient.disconnect()
                }
            } else {
                println("ERROR ${httpClient.responseCode}")
            }
            return null
        }

        fun readStream(inputStream: BufferedInputStream): String {
            val bufferedReader = BufferedReader(InputStreamReader(inputStream))
            val stringBuilder = StringBuilder()
            bufferedReader.forEachLine { stringBuilder.append(it) }
            return stringBuilder.toString()
        }

        override fun onPreExecute() {
            super.onPreExecute()
        }

        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)

            innerTextView?.text = result

        }

    }
}
