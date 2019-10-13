package com.xtha.zujal.usernotification

import android.app.*
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.graphics.Color
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.NotificationCompat
import android.support.v4.app.NotificationManagerCompat
import android.view.View
import android.widget.Button
import android.widget.RemoteViews


// To know more about Android notifation go the following link. This will help you with what are the possibilites of android notifcation
//https://developer.android.com/guide/topics/ui/notifiers/notifications

class MainActivity : AppCompatActivity() {


    lateinit var notificationManager: NotificationManager
    lateinit var notificationChannel: NotificationChannel
    lateinit var builder1: NotificationCompat.Builder
    lateinit var builder2: NotificationCompat.Builder
    lateinit var builder3: NotificationCompat.Builder
    lateinit var builder4: NotificationCompat.Builder
    private val channelId = "com.xtha.zujal.usernotification"
    private val description = "Test notification"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var Button1: Button  = findViewById(R.id.button1)
        var Button2: Button = findViewById(R.id.button2)
        var Button3: Button = findViewById(R.id.button3)
        var Button4: Button = findViewById(R.id.button4)
        var Button5: Button = findViewById(R.id.button5)
        var Button6: Button = findViewById(R.id.button6)
        var Button7: Button = findViewById(R.id.button7)


        // Creating a notication channel

        notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val intent = Intent(this, MainActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            notificationChannel = NotificationChannel(channelId, description, NotificationManager.IMPORTANCE_HIGH)
            notificationChannel.enableLights(true)
            notificationChannel.lightColor = Color.GREEN
            notificationChannel.enableVibration(false)
            notificationManager.createNotificationChannel(notificationChannel)


            Button1.setOnClickListener {

                // First Notification
                // simple notication with icon title and text
                builder1 = NotificationCompat.Builder(this, channelId)
                    .setSmallIcon(R.drawable.notification_icon_background)
                    .setContentTitle("First Notification")
                    .setContentText("First notification text Much longer text that cannot fit one line...")
                    .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                notificationManager.notify(1234, builder1.build())
            }

            Button2.setOnClickListener {

                //Second Notification
                //notifcation with Large icon and longer text
                builder2 = NotificationCompat.Builder(this, channelId)
                    .setSmallIcon(R.drawable.notification_icon_background)
                    .setContentTitle("Second notification")
                    .setStyle(
                        NotificationCompat.BigTextStyle()
                            .bigText("Much longer text that cannot fit oh longer text that cannot fit oMuch longer text that cannot fit oh longer text that cannot fine line...")
                    )
                    .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                    .setLargeIcon(
                        BitmapFactory.decodeResource(
                            this.resources,
                            R.drawable.notify_panel_notification_icon_bg
                        )
                    )
                notificationManager.notify(12344, builder2.build())
            }

            Button3.setOnClickListener {

                // Third Notifcation
                // Notification which clicked opens and activity
                val intent = Intent(this, NotifcationScreen::class.java).apply {
                    flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                }

                builder3 = NotificationCompat.Builder(this, channelId)
                    .setSmallIcon(R.drawable.notification_icon_background)
                    .setContentTitle("Third notification")
                    .setContentText("Hello World!")
                    .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                    // Set the intent that will fire when the user taps the notification
                    .setContentIntent(pendingIntent)
                    .setAutoCancel(true)

                notificationManager.notify(123244, builder3.build())
            }

            Button4.setOnClickListener {

                // Fourth Notification
                // Notification with Action Button

                //  val snoozeIntent = Intent(this, MyBroadcastReceiver::class.java).apply {
                //      action = ACTION_SNOOZE
                //       putExtra(EXTRA_NOTIFICATION_ID, 0)
                //  }
                //   val snoozePendingIntent: PendingIntent =
                //       PendingIntent.getBroadcast(this, 0, snoozeIntent, 0)

                builder4 = NotificationCompat.Builder(this, channelId)
                    .setSmallIcon(R.drawable.notification_icon_background)
                    .setContentTitle("My notification")
                    .setContentText("Hello World!")
                    .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                    .setContentIntent(pendingIntent)
                    .addAction(
                        R.drawable.notification_icon_background, "SNOOZE",
                        pendingIntent
                    )

                notificationManager.notify(1232444, builder4.build())

            }

            Button5.setOnClickListener {
                // Fifth Notification
                // Notifaction where you could reply with text message
                val KEY_TEXT_REPLY = "key_text_reply"
                var replyLabel: String = "REPLY"
                var remoteInput: RemoteInput = RemoteInput.Builder(KEY_TEXT_REPLY).run {
                    setLabel(replyLabel)
                    build()
                }

                var replyPendingIntent: PendingIntent =
                    PendingIntent.getBroadcast(
                        applicationContext,
                        12,
                        getIntent(),
                        PendingIntent.FLAG_UPDATE_CURRENT
                    )

                var action: Notification.Action =
                    Notification.Action.Builder(
                        R.drawable.notification_icon_background,
                        "REPLY", replyPendingIntent
                    )
                        .addRemoteInput(remoteInput)
                        .build()

                val builder5 = Notification.Builder(this, channelId)
                    .setSmallIcon(R.drawable.notification_icon_background)
                    .setContentTitle("Fifth Notification")
                    .setContentText("he  bhak yo")
                    .addAction(action)


                notificationManager.notify(12324444, builder5.build())

                fun getMessageText(intent: Intent): CharSequence? {
                    return RemoteInput.getResultsFromIntent(intent)?.getCharSequence(KEY_TEXT_REPLY)
                }

                val repliedNotification = Notification.Builder(this, channelId)
                    .setSmallIcon(R.drawable.notification_icon_background)
                    .setContentText("Replied")
                    .build()

                // Issue the new notification.
                NotificationManagerCompat.from(this).apply {
                    notificationManager.notify(12121, repliedNotification)
                }

            }

            Button6.setOnClickListener {

                // Sixth Notification
                // Notification with Progress bar
                val builder6 = NotificationCompat.Builder(this, channelId).apply {
                    setContentTitle("Picture Download")
                    setContentText("Download in progress")
                    setSmallIcon(R.drawable.notification_icon_background)
                    setPriority(NotificationCompat.PRIORITY_LOW)
                }
                val PROGRESS_MAX = 100
                val PROGRESS_CURRENT = 0
                NotificationManagerCompat.from(this).apply {
                    // Issue the initial notification with zero progress
                    builder6.setProgress(PROGRESS_MAX, PROGRESS_CURRENT, false)
                    notify(12151, builder6.build())

                    // Do the job here that tracks the progress.
                    // Usually, this should be in a
                    // worker thread
                    // To show progress, update PROGRESS_CURRENT and update the notification with:
                    // builder.setProgress(PROGRESS_MAX, PROGRESS_CURRENT, false);
                    // notificationManager.notify(notificationId, builder.build());

                    // When done, update the notification one more time to remove the progress bar
                    builder6.setContentText("Download complete")
                        .setProgress(0, 0, false)
                    notify(42515, builder6.build())
                }

            }

            Button7.setOnClickListener {
                /* Set a system - wide category
                    Android uses a some pre - defined system -wide categories to determine whether to disturb
                    the user with a given notification when the user has enabled Do Not Disturb mode.If your
                     notification falls into one of the pre - defined notification categories defined in NotificationCompat —
                     such as CATEGORY_ALARM, CATEGORY_REMINDER, CATEGORY_EVENT, or CATEGORY_CALL—you should declare it as such by
                     passing the appropriate category to setCategory().

            */


                var builder7 = NotificationCompat.Builder(this, channelId)
                    .setSmallIcon(R.drawable.notification_icon_background)
                    .setContentTitle("My notification")
                    .setContentText("Hello World!")
                    .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                    .setCategory(NotificationCompat.CATEGORY_MESSAGE)

                notificationManager.notify(144424, builder7.build())
            }


            /*Set lock screen visibility
                    To control the level of detail visible in the notification
                    from the lock screen, call setVisibility() and specify one of the following values:

            VISIBILITY_PUBLIC shows the notification's full content.
            VISIBILITY_SECRET doesn't show any part of this notification on the lock screen.
            VISIBILITY_PRIVATE shows basic information, such as the notification's icon and
            the content title, but hides the notification's full content.
            When VISIBILITY_PRIVATE is set, you can also provide an alternate version of the
            notification content which hides certain details. For example, an SMS app might
            display a notification that shows You have 3 new text messages, but hides the message
            contents and senders. To provide this alternative notification, first create the
            alternative notification with NotificationCompat.Builder as usual. Then attach the
             alternative notification to the normal notification with setPublicVersion().

            However, the user always has final control over whether their notifications are visible on the lock screen and can even control that based on your app's notification channels.
        */

            /* Update a notification
         To update this notification after you've issued it, call NotificationManagerCompat.notify()
         again, passing it a notification with the same ID you used previously. If the previous
         notification has been dismissed, a new notification is created instead.

         You can optionally call setOnlyAlertOnce() so your notification interupts the user (with sound,
          vibration, or visual clues) only the first time the notification appears and not for later updates.
 */

            /*  Remove a notification
              Notifications remain visible until one of the following happens:

              The user dismisses the notification.
                  The user clicks the notification, and you called setAutoCancel()
                  when you created the notification.
              You call cancel() for a specific notification ID. This method also deletes ongoing notifications.
                  You call cancelAll(), which removes all of the notifications you previously issued.
              If you set a timeout when creating a notification using setTimeoutAfter(), the system
              cancels the notification after the specified duration elapses. If required, you can
              cancel a notification before the specified timeout duration elapses.
      */
        }


       // notificationManager.notify(1234, builder1.build())
       // notificationManager.notify(12344, builder2.build())
       // notificationManager.notify(123244, builder3.build())
        //notificationManager.notify(1232444, builder4.build())
    }


}