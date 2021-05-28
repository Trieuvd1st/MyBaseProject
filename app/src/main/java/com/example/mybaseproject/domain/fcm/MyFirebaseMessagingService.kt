package com.example.mybaseproject.domain.fcm

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import android.os.Build
import android.widget.Toast
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.mybaseproject.R
import com.example.mybaseproject.ui.second.SecondActivity
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.messaging.FirebaseMessaging
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import timber.log.Timber

class MyFirebaseMessagingService: FirebaseMessagingService() {

    override fun onMessageReceived(p0: RemoteMessage) {
        super.onMessageReceived(p0)
        Timber.d("FcmInstance onMessageReceived() called: ${p0.data}")
        createNotification(p0.notification?.title ?: "", p0.notification?.body ?: "")
    }

    private fun createNotification(title: String, message: String) {
        //create a intent open screen when click notification
        val intent = Intent(this, SecondActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        val pendingIntent = PendingIntent.getActivity(
            this,
            NOTIFY_ID, intent, 0
        )
        //create a notification
        val mBuilder =
            NotificationCompat.Builder(this, CHANNEL_ID).apply {
                setContentIntent(pendingIntent)
            }
                .setSmallIcon(R.drawable.ic_noti)
                .setContentTitle(title)
                .setContentText(message)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setGroup(GROUP_KEY_DEMO)

        val notification = mBuilder.build()

        //create channel notification (> android 8)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name: CharSequence = "notification android 8"
            val description = "notification android 8"
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel =
                NotificationChannel(CHANNEL_ID, name, importance)
            channel.description = description
        }

        //create summary notification, wrap all notification with same key(setGroup)
        val summaryNotification = NotificationCompat.Builder(this, CHANNEL_ID)
            .setContentTitle("FirebaseFCM_Demo")
            .setContentText("Two new messages")
            .setSmallIcon(R.drawable.ic_group)
            .setStyle(
                NotificationCompat.InboxStyle()
//                        .addLine("Alex Faarborg Check this out")
//                        .addLine("Jeff Chang Launch Party")
//                        .setBigContentTitle("2 new messages")
                    .setSummaryText("FireBaseFCM Demo")
            )
            .setGroup(GROUP_KEY_DEMO)
            .setGroupSummary(true)
            .build()
//            val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
//            notificationManager.createNotificationChannel(channel)
//            notificationManager.notify(MyFirebaseMessagingService.NOTIFY_ID++, notification)

        NotificationManagerCompat.from(this).apply {
            notify(NOTIFY_ID++, notification)
            notify(123, summaryNotification)
        }
    }

    override fun onNewToken(p0: String) {
        FirebaseMessaging.getInstance().token.addOnCompleteListener(OnCompleteListener { task ->
            if (!task.isSuccessful) {
                return@OnCompleteListener
            }
            val token = task.result
            Toast.makeText(baseContext, "token: $token", Toast.LENGTH_SHORT).show()
        })
    }

    companion object {
        var NOTIFY_ID = 256
        const val CHANNEL_ID = "123"
        const val GROUP_KEY_DEMO = "com.example.firebaseFCM.DEMO"
    }
}