package kr.ac.dankook.mobile.prof.jeong.timer

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.util.Log
import android.widget.Button
import android.widget.TextView

//class MyHandler : Handler(Looper.getMainLooper()) {
//        override fun handleMessage(msg: Message) {
//            super.handleMessage(msg)
////            val helloText = findViewById<TextView>(R.id.helloText)
//
//            Log.d("BkgThread", "Main thread")
////            if(msg.what == 1) {
////                helloText.setText("$msg.arg1")
////            }
//        }
//}

var started = false
var paused = false
var stoped = false
var pauseClick = 0

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val timerText = findViewById<TextView>(R.id.timerText)
        val buttonStart = findViewById<Button>(R.id.buttonStart)
        val buttonStop = findViewById<Button>(R.id.buttonStop)
        val buttonPause = findViewById<Button>(R.id.buttonPause)

        val sharedPref = getSharedPreferences("kr.ac.dankook.example.SHARED_PREF", Context.MODE_PRIVATE);
        val editor = sharedPref.edit()

        var i = sharedPref.getInt("pauseTime", 0)
        Log.d("BkgThread", "pauseTime is $i")

        val firstMin = String.format("%02d", i/60)
        val firstSec = String.format("%02d", i%60)
        timerText.text = "$firstMin:$firstSec"

        val myHandler = object : Handler(Looper.getMainLooper()) {
            override fun handleMessage(msg: Message) {
                super.handleMessage(msg)

                Log.d("BkgThread", "Main thread")
                if(msg.what == 1) {
                    val min = String.format("%02d", msg.arg1/60)
                    val sec = String.format("%02d", msg.arg1%60)
                    timerText.text = "$min:$sec"
                }
            }
        }

        Thread {
            while(true) {
                Thread.sleep(1000)
                if(started) {
                    i += 1
                    Log.d("BkgThread", "In background thread : $i")
                    var msg = myHandler.obtainMessage()
                    msg.what = 1
                    msg.arg1 = i
                    myHandler.sendMessage(msg)
                }
                if(paused) {
                    if (pauseClick % 2 == 1) {

                    } else{
                        i += 1
                        var msg = myHandler.obtainMessage()
                        msg.what = 1
                        msg.arg1 = i
                        myHandler.sendMessage(msg)
                    }
                }

                if(stoped){
                    i = 0
                }
            }
        }.start()

        buttonStart.setOnClickListener {
            started = true
            stoped = false
            paused = false

            pauseClick = 0
        }

        buttonStop.setOnClickListener {
            started = false
            stoped = true
            paused = false

            pauseClick = 0
        }

        buttonPause.setOnClickListener {
            started = false
            paused = true
            stoped = false

            pauseClick += 1

            editor.putInt("pauseTime", i)

            editor.apply()
        }
    }
}