package com.example.jayden.kotlin_http_get

import android.os.AsyncTask
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
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
        val result = findViewById(R.id.result) as TextView

        // new AsyncTask
        httpGET.setOnClickListener {
            HTTPTask(result).execute()
        }

    }

    class HTTPTask(textView: TextView) : AsyncTask<Unit, Unit, String>() {

        val innerTextView: TextView? = textView

        override fun doInBackground(vararg params: Unit?): String {
            // localhost server address
            val url = URL("http://192.168.0.126:8888/kotlin.txt")
            val httpClient = url.openConnection() as HttpURLConnection
            if (httpClient.responseCode == HttpURLConnection.HTTP_OK) {
                try {
                    val stream = BufferedInputStream(httpClient.inputStream)
                    val data: String = readStream(inputStream = stream)
                    return data

                }catch (e: Exception) {
                    e.printStackTrace()
                }finally {
                    httpClient.disconnect()
                }

            }else{
                println("ERROR ${httpClient.responseCode}")
            }
            return null!!
        }

        // show result on TextView
        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)
            innerTextView?.text = result
        }

        override fun onPreExecute() {
            super.onPreExecute()

        }

        // read inputStream method
        fun readStream(inputStream: BufferedInputStream): String {
            val bufferedReader = BufferedReader(InputStreamReader(inputStream))
            val stringBuilder = StringBuilder()
            bufferedReader.forEachLine { stringBuilder.append(it) }
            return stringBuilder.toString()
        }

    }
}
