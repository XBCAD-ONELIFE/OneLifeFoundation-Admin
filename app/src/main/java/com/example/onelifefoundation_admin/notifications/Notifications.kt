package com.example.onelifefoundation_admin.notifications

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.graphics.Color
import android.os.Build
import androidx.compose.runtime.Composable
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.IconCompat
import androidx.core.graphics.drawable.toBitmap
import androidx.navigation.NavController
import com.example.onelifefoundation_admin.R

class Notifications(private val context: Context) {

    private val channelID = "Your_Channel_ID"
    private val description = "Your_Channel_Description"

    private val notificationManager: NotificationManager by lazy {
        context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
    }

    init {
        createNotificationChannel()
    }



    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val notificationChannel = NotificationChannel(
                channelID,
                description,
                NotificationManager.IMPORTANCE_DEFAULT
            )
            notificationChannel.enableLights(true)
            notificationChannel.lightColor = Color.GREEN
            notificationChannel.enableVibration(true)
            notificationManager.createNotificationChannel(notificationChannel)
        }
    }




    fun showNotification(title: String, content: String, navController: NavController) {
        val builder = NotificationCompat.Builder(context, channelID)
            .setContentTitle(title)
            .setContentText(content)
            .setSmallIcon(R.mipmap.ic_launcher_round)
            .setColor(ContextCompat.getColor(context, com.airbnb.lottie.R.color.abc_btn_colored_text_material))
            .setAutoCancel(true)

        // Create a notification action if needed
        /*val notificationIntent = Intent(context, YourActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(
            context, 0, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT
        )
        builder.setContentIntent(pendingIntent)*/

        notificationManager.notify(1, builder.build())
    }
}
