package com.example.jayden.radioplayer

import android.media.MediaPlayer
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button

class MainActivity : AppCompatActivity() {

    val urlPath = "http://stm2.rthk.hk/radio2"
    lateinit var btnPlay: Button
    lateinit var btnPause: Button
    lateinit var mediaPlayer: MediaPlayer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnPlay = findViewById(R.id.btnPlay)
        btnPause = findViewById(R.id.btnPause)

        mediaPlayer = MediaPlayer()

        mediaPlayer.setOnPreparedListener {
            mediaPlayer.start()
        }

        mediaPlayer.setOnErrorListener(object : MediaPlayer.OnErrorListener {
            override fun onError(p0: MediaPlayer?, p1: Int, p2: Int): Boolean {
                mediaPlayer.reset()
                return false
            }

        })

        btnPlay.setOnClickListener {
            mediaPlayer.setDataSource(urlPath)
            mediaPlayer.prepareAsync()
        }

        btnPause.setOnClickListener {
            mediaPlayer.reset()
        }

    }
}
