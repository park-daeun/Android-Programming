package com.example.myappweek13_app2

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnStart = findViewById<Button>(R.id.btnStart)
        val btnEnd = findViewById<Button>(R.id.btnEnd)
        val btnGetData = findViewById<Button>(R.id.btnGetData)
        val dataView = findViewById<TextView>(R.id.dataView)

        btnStart.setOnClickListener {
            val intent = Intent(this, MyService::class.java)
            bindService(intent, connection, Context.BIND_AUTO_CREATE)
        }
        btnEnd.setOnClickListener {
            if(isService) {
                unbindService(connection)
                isService = false
            }
        }
        btnGetData.setOnClickListener {
            if(isService) {
                var num = myService?.getNumber()
                dataView.text = "Get number $num"
            } else {
                dataView.text = "Service is not running"
            }
        }
    }

    private var myService : MyService? = null
    private var isService = false
    val connection = object : ServiceConnection {
        override fun onServiceConnected(p0: ComponentName?, p1: IBinder?) {
            val binder = MyService().MyBinder()
            myService = binder.getService()
            isService = true
            Log.d("BOUNDSERVICE","Service connected")
        }
        override fun onServiceDisconnected(p0: ComponentName?) {
            isService = false
            Log.d("BOUNDSERVICE","Service disconnected")
        }
    }
}