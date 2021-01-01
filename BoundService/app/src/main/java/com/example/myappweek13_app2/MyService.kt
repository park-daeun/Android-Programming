package com.example.myappweek13_app2

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import android.util.Log

class MyService : Service() {

    inner class MyBinder : Binder() {
        fun getService() : MyService {
            return this@MyService
        }
    }

    private val binder = MyBinder()

    override fun onBind(intent: Intent): IBinder {
        Log.d("BOUNDSERVICE","onBind")
        return binder
    }

    override fun onUnbind(intent: Intent?): Boolean {
        Log.d("BOUNDSERVICE","onUnBind")
        return super.onUnbind(intent)
    }

    private var num=0;
    fun getNumber() : Int {
        num +=1;
        return num;
    }
}