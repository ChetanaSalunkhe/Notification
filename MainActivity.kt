package com.example.notification

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.annotation.RequiresApi
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var notificationManager : NotificationManager? = null

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //initialise notification manager
        notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        //create notification channel
        createNotificationChannel("com.example.notification",
            "Notification Kotlin",
            "Hello you are receiving this notification developed in kotlin language")

        btnnotify.setOnClickListener {
            sendNotification()
        }

    }

    //notification channel creation
    @RequiresApi(Build.VERSION_CODES.O)
    private fun createNotificationChannel(id:String, name:String, description:String){

        val importance = NotificationManager.IMPORTANCE_LOW
        val channel = NotificationChannel(id, name, importance)

        channel.description = description
        channel.enableLights(true)
        channel.lightColor = Color.RED
        channel.enableVibration(true)
        channel.vibrationPattern = longArrayOf(100,200,300,400,500,400,300,200,100)
        notificationManager?.createNotificationChannel(channel)

    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun sendNotification(){

        val notificationId = 101

        val channelId = "com.example.notification"

        val notification = Notification.Builder(this, channelId)
            .setContentTitle("Hello Chetana")
            .setContentText("Hello you are receiving this notification developed in kotlin language.")
            .setSmallIcon(R.drawable.notify)
            .setChannelId(channelId)
            .build()

        notificationManager?.notify(notificationId,notification)

    }
}
