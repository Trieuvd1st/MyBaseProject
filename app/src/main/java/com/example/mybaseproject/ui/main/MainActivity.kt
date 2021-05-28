package com.example.mybaseproject.ui.main

import android.content.Intent
import android.os.Bundle
import com.example.mybaseproject.R
import com.example.mybaseproject.base.BaseActivity
import com.example.mybaseproject.ui.second.SecondActivity
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {

    private lateinit var mFirebaseAnalytics: FirebaseAnalytics

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mFirebaseAnalytics = Firebase.analytics

        btnOpenSecond.setOnClickListener {
            startActivity(Intent(this, SecondActivity::class.java))
            /*val bundle = Bundle()
            bundle.putString(FirebaseAnalytics.Param.SCREEN_NAME, "MainActivity")
            bundle.putString(FirebaseAnalytics.Param.SCREEN_CLASS, "MainActivity")
            mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SCREEN_VIEW, bundle)*/
        }

        btnPushNotification.setOnClickListener {
            //createNotification(edtTitle.text.toString(), edtMessage.text.toString())
            val bundle = Bundle()
            bundle.putString(FirebaseAnalytics.Param.ITEM_ID, "1")
            bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, "trieuvd")
            bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, "image")
            mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle)
        }

        btnDoCrash.setOnClickListener {
            throw RuntimeException("Test Crash")
        }
    }

//    private fun createNotification(title: String, message: String) {
//        //create a intent open screen when click notification
//        val intent = Intent(this, SecondActivity::class.java).apply {
//            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
//        }
//        val pendingIntent = PendingIntent.getActivity(
//            this,
//            MyFirebaseMessagingService.NOTIFY_ID, intent, 0
//        )
//        //create a notification
//        val mBuilder =
//            NotificationCompat.Builder(this, MyFirebaseMessagingService.CHANNEL_ID).apply {
//                setContentIntent(pendingIntent)
//            }
//                .setSmallIcon(R.drawable.ic_noti)
//                .setContentTitle(title)
//                .setContentText(message)
//                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
//                .setGroup(MyFirebaseMessagingService.GROUP_KEY_DEMO)
//
//        val notification = mBuilder.build()
//
//        //create channel notification (> android 8)
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//            val name: CharSequence = "notification android 8"
//            val description = "notification android 8"
//            val importance = NotificationManager.IMPORTANCE_DEFAULT
//            val channel =
//                NotificationChannel(MyFirebaseMessagingService.CHANNEL_ID, name, importance)
//            channel.description = description
//        }
//
//        //create summary notification, wrap all notification with same key(setGroup)
//        val summaryNotification = NotificationCompat.Builder(
//            this,
//            MyFirebaseMessagingService.CHANNEL_ID
//        )
//            .setContentTitle("FirebaseFCM_Demo")
//            .setContentText("Two new messages")
//            .setSmallIcon(R.drawable.ic_group)
//            .setStyle(
//                NotificationCompat.InboxStyle()
////                        .addLine("Alex Faarborg Check this out")
////                        .addLine("Jeff Chang Launch Party")
////                        .setBigContentTitle("2 new messages")
//                    .setSummaryText("FireBaseFCM Demo")
//            )
//            .setGroup(MyFirebaseMessagingService.GROUP_KEY_DEMO)
//            .setGroupSummary(true)
//            .build()
////            val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
////            notificationManager.createNotificationChannel(channel)
////            notificationManager.notify(MyFirebaseMessagingService.NOTIFY_ID++, notification)
//
//        NotificationManagerCompat.from(this).apply {
//            notify(MyFirebaseMessagingService.NOTIFY_ID++, notification)
//            notify(123, summaryNotification)
//        }
//    }
}