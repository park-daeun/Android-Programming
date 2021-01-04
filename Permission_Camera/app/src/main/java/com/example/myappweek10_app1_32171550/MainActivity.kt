package com.example.myappweek10_app1_32171550

import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import java.util.jar.Manifest

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnCamera = findViewById<Button>(R.id.btnCamera)
        var textView2 = findViewById<TextView>(R.id.textView2)
        btnCamera.setOnClickListener{
            Log.d("DKMobile","CAMERA button pressed.")
            val cameraPermission = ContextCompat.checkSelfPermission(this, android.Manifest.permission.CAMERA)
            if(cameraPermission == PackageManager.PERMISSION_GRANTED){
                Log.d("DKMobile", "CAMERA permission already granted")
                textView2.text = "CAMERA permission already granted"
            } else {
                ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.CAMERA), 99)
            }
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        var textView2 = findViewById<TextView>(R.id.textView2)
        when(requestCode){
            99 ->{
                if(grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    Log.d("DKMobile", "CAMERA permission granted now")
                    textView2.text = "CAMERA permission granted now"
                } else{
                    Log.d("DKMobile", "CAMERA permission not granted")
                    textView2.text = "CAMERA permission not granted"
                }
            }
        }
    }
}
