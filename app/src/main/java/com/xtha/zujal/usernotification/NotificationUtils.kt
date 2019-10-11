package com.xtha.zujal.usernotification

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.ContextWrapper
import android.graphics.Color
import android.os.Build
import com.xtha.zujal.usernotification.NotificationUtils.Companion.ANDROID_CHANNEL_ID
import com.xtha.zujal.usernotification.NotificationUtils.Companion.IOS_CHANNEL_ID

class NotificationUtils(base: Context) : ContextWrapper(base) {

    private var mManager: NotificationManager? = null

    private val manager: NotificationManager?
        get() {
            if (mManager == null) {
                mManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            }
            return mManager
        }

    init {
        createChannels()
    }

    fun createChannels() {


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            // create android channel
            val androidChannel = NotificationChannel(
                ANDROID_CHANNEL_ID,
                ANDROID_CHANNEL_NAME, NotificationManager.IMPORTANCE_DEFAULT
            )
            // Sets whether notifications posted to this channel should display notification lights
            androidChannel.enableLights(true)
            // Sets whether notification posted to this channel should vibrate.
            androidChannel.enableVibration(true)
            // Sets the notification light color for notifications posted to this channel
            androidChannel.lightColor = Color.GREEN
            // Sets whether notifications posted to this channel appear on the lockscreen or not
            androidChannel.lockscreenVisibility = Notification.VISIBILITY_PRIVATE

            manager!!.createNotificationChannel(androidChannel)

            // create ios channel
            val iosChannel = NotificationChannel(
                IOS_CHANNEL_ID,
                IOS_CHANNEL_NAME, NotificationManager.IMPORTANCE_HIGH
            )
            iosChannel.enableLights(true)
            iosChannel.enableVibration(true)
            iosChannel.lightColor = Color.GRAY
            iosChannel.lockscreenVisibility = Notification.VISIBILITY_PUBLIC
            manager!!.createNotificationChannel(iosChannel)
        }

    }

    companion object {
        val ANDROID_CHANNEL_ID = "com.chikeandroid.tutsplustalerts.ANDROID"
        val IOS_CHANNEL_ID = "com.chikeandroid.tutsplustalerts.IOS"
        val ANDROID_CHANNEL_NAME = "ANDROID CHANNEL"
        val IOS_CHANNEL_NAME = "IOS CHANNEL"
    }

    fun getAndroidChannelNotification(title: String, body: String): Notification.Builder {
        return Notification.Builder(getApplicationContext(), ANDROID_CHANNEL_ID)
            .setContentTitle(title)
            .setContentText(body)
            .setSmallIcon(android.R.drawable.stat_notify_more)
            .setAutoCancel(true)
    }

    fun getIosChannelNotification(title: String, body: String): Notification.Builder {
        return Notification.Builder(getApplicationContext(), IOS_CHANNEL_ID)
            .setContentTitle(title)
            .setContentText(body)
            .setSmallIcon(android.R.drawable.stat_notify_more)
            .setAutoCancel(true)
    }
}

