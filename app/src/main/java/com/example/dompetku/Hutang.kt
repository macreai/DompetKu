package com.example.dompetku

import android.app.AlarmManager
import android.app.AlertDialog
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.dompetku.databinding.ActivityHutangBinding
import java.util.*

class Hutang : AppCompatActivity() {

    private lateinit var binding: ActivityHutangBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHutangBinding.inflate(layoutInflater)
        setContentView(binding.root)

        createNotificationChannel()
        binding.btnReminder.setOnClickListener{scheduleNotification()}
    }

    private fun scheduleNotification() {
        val intent = Intent(applicationContext, Notification::class.java)
        val item = binding.hutang.text.toString()
        val desc = binding.desc.text.toString()
        intent.putExtra(itemExtra, item)
        intent.putExtra(descExtra, desc)

        val pendingIntent = PendingIntent.getBroadcast(
            applicationContext,
            notificationID,
            intent,
            PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
        )

        val alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val time = getTime()
        alarmManager.setExactAndAllowWhileIdle(
            AlarmManager.RTC_WAKEUP,
            time,
            pendingIntent
        )
        showAlert(time, item, desc)
    }

    private fun showAlert(time: Long, item: String, desc: String) {
        val date = Date(time)
        val dateFormat = android.text.format.DateFormat.getLongDateFormat(applicationContext)
        val timeFormat = android.text.format.DateFormat.getTimeFormat(applicationContext)

        AlertDialog.Builder(this)
            .setTitle("Reminder sudah dibuat")
            .setMessage(
                "Item: " + item +
                "\nDeskripsi: " + desc +
                "\nPada: " + dateFormat.format(date) + " " + timeFormat.format(date)
            )
            .setPositiveButton("Okay"){_,_->}
            .show()
    }

    private fun getTime(): Long {
        val minute = binding.timePicker.minute
        val hour = binding.timePicker.hour
        val day = binding.datePicker.dayOfMonth
        val month = binding.datePicker.month
        val year = binding.datePicker.year

        val calendar = Calendar.getInstance()
        calendar.set(year, month, day, hour, minute)
        return calendar.timeInMillis
    }

    private fun createNotificationChannel() {
        val item = "Notif Channel"
        val desc = "A description of item"
        val importance = NotificationManager.IMPORTANCE_DEFAULT
        val channel = NotificationChannel(channelID, item, importance)
        channel.description = desc
        val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(channel)
    }
}