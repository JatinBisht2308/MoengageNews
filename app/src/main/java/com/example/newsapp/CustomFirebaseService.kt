package com.example.newsapp

import android.util.Log
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class CustomFirebaseService : FirebaseMessagingService() {

    /**
     * Called when a new FCM token is generated for the device.
     *
     * @param token The new FCM token for the device.
     */

    override fun onNewToken(token: String) {
        Log.d("testing",token)
        super.onNewToken(token)
    }

    /**
     * Called when a new FCM message is received.
     *
     * @param message The received FCM message.
     */

    override fun onMessageReceived(message: RemoteMessage) {
        Log.d("testing",message.toString())
        // Build and display a notification based on the received FCM message
            NotificationService.notificationBuilder(this, message.notification?.title?:"", message.notification?.body?:"")
    }
}