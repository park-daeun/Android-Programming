package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.util.Log
import android.widget.Button
import android.widget.TextView

var started = false

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val timerText = findViewById<TextView>(R.id.timerText)
        val buttonStart = findViewById<Button>(R.id.buttonStart)
        val buttonStop = findViewById<Button>(R.id.buttonStop)

        val myHandler = object : Handler(Looper.getMainLooper()){
            override fun handleMessage(msg: Message){
                super.handleMessage(msg)
                Log.d("BkgThread", "Main thread")
                if(msg.what == 1){
                    val min = String.format("%02d", msg.arg1/60)
                    val sec = String.format("%02d", msg.arg1%60)
                    timerText.text = "$min:$sec"
                }
            }
        }

        Thread{
            var i = 0
            while(true){
                Thread.sleep(1000)
                if(started){
                    i += 1
                    Log.d("BkgThread", "In background thread: $i")
                    var msg = myHandler.obtainMessage()
                    msg.what = 1
                    msg.arg1 = i
                    myHandler.sendMessage(msg)
                } else {
                    i = 0
                }
            }
        }.start()

        buttonStart.setOnClickListener {
            started = true
        }
        buttonStop.setOnClickListener {
            started = false
        }
    }
}