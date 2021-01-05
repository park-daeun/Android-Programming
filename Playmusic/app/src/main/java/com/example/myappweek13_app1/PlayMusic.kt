package com.example.myappweek13_app1

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.Build
import android.os.IBinder
import android.util.Log
import androidx.core.app.NotificationCompat

class PlayMusic : Service() {
    private var mediaPlayer: MediaPlayer? = null
    private var channelid = "PLAYMUSIC"

    override fun onBind(intent: Intent): IBinder {
        TODO("Return the communication channel to the service.")
    }

    override fun onCreate() {
        super.onCreate()
        Log.d("PlayMusic", "Started")
    }
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        val play = intent?.getBooleanExtra("play", false)
        if (play == true) {
            Log.d("PlayMusic", "Play music")
            mediaPlayer = MediaPlayer.create(this, R.raw.dean)
            mediaPlayer?.start()
            createNotificationChannel()
            val notification = NotificationCompat.Builder(this, channelid)
                .setContentTitle("Play Music")
                .setSmallIcon(R.mipmap.ic_launcher_round)
                .build()
            startForeground(1, notification)
        } else {
            Log.d("PlayMusic", "Stop play music")
            mediaPlayer?.stop()
            mediaPlayer?.release()
        }
        return super.onStartCommand(intent, flags, startId)
    }

    private fun createNotificationChannel() {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            val serviceChannel = NotificationChannel(channelid, "Play Music",
                NotificationManager.IMPORTANCE_DEFAULT)
            val manager = getSystemService(NotificationManager::class.java)
            manager!!.createNotificationChannel(serviceChannel)
        }
    }
}
