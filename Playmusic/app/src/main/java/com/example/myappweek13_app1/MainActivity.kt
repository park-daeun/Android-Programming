package com.example.myappweek13_app1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnPlay = findViewById<Button>(R.id.btnPlay)
        val btnStop = findViewById<Button>(R.id.btnStop)

        btnPlay.setOnClickListener{
            val intentPlay = Intent(this, PlayMusic::class.java)
            intentPlay.putExtra("play", true)
            startService(intentPlay)
        }

        btnStop.setOnClickListener {
            val intentPlay = Intent(this, PlayMusic::class.java)
            intentPlay.putExtra("play", false)
            startService(intentPlay)
        }
    }
}