package com.example.myappweek9_app2_32171550

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.location.Location
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.d("DKMOBILE", "MainActivity onCreate()")

        val intentMain = Intent(this, SubActivity::class.java)

        val openSub = findViewById<Button>(R.id.openSub)

        intentMain.putExtra("num1", 10)
        intentMain.putExtra("num2", 20)

        openSub.setOnClickListener{
            Log.d("DKMobile", "OPEN SUB button pressed.")
            startActivityForResult(intentMain, 0)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode == Activity.RESULT_OK){

            val resultval = data?.getIntExtra("result", 0)

            var text1 = findViewById<TextView>(R.id.textView)
                text1.setText("Get result value $resultval")
        }
    }

    override fun onStart(){
        super.onStart()
        Log.d("DKMobile", "MainActivity onStart()")
    }

    override fun onResume(){
        super.onResume()
        Log.d("DKMobile", "MainActivity onResume()")
    }

    override fun onPause(){
        super.onPause()
        Log.d("DKMobile", "MainActivity onPause()")
    }

    override fun onStop(){
        super.onStop()
        Log.d("DKMobile", "MainActivity onStop()")
    }
}