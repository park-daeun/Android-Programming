package com.example.myappweek9_app2_32171550

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_sub.*

class SubActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sub)

        Log.d("DKMOBILE", "Sub activity created!")

        val intentSub = intent

        val num1 = intentSub.getIntExtra("num1", 0)
        val num2 = intentSub.getIntExtra("num2", 0)
        val plusres = num1 + num2

        var text2 = findViewById<TextView>(R.id.textView2)
            text2.setText("$num1 + $num2 = ${plusres}")

        val backBtn = findViewById<Button>(R.id.backBtn)
        backBtn.setOnClickListener{
            val outIntent = Intent (this, MainActivity::class.java)

            outIntent.putExtra("result", plusres)

            setResult(Activity.RESULT_OK, outIntent)

            Log.d("DKMobile", "BACK Button pressed.")
            finish()
        }
    }

    override fun onStart(){
        super.onStart()
        Log.d("DKMobile", "SubActivity onStart()")
    }

    override fun onResume(){
        super.onResume()
        Log.d("DKMobile", "SubActivity onResume()")
    }

    override fun onPause(){
        super.onPause()
        Log.d("DKMobile", "SubActivity onPause()")
    }

    override fun onStop(){
        super.onStop()
        Log.d("DKMobile", "SubActivity onStop()")
    }
}