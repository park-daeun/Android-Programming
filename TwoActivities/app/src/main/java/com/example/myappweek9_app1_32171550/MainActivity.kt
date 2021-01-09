package com.example.myappweek9_app1_32171550

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val intent = Intent(this, SubActivity::class.java)

        val openSub = findViewById<Button>(R.id.openSub)
        openSub.setOnClickListener {
            Log.d("DKMobile", "OPEN SUB button pressed.")
            startActivity(intent)
        }
    }
}