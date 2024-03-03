package github.vodianov.hoursignal.service

import android.app.NotificationManager
import android.content.Context
import android.os.BatteryManager
import android.provider.Settings
import android.util.Log

class DeviceInfoService(private val context: Context) {

    private val logTag = "DeviceInfoService"

    fun isDoNotDisturbOn() : Boolean {
        val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        val isDoNotDisturb = notificationManager.currentInterruptionFilter != 1
        Log.d(logTag, String.format("is do not disturb enable: %s", isDoNotDisturb))

        return isDoNotDisturb
    }

    fun isCharging() : Boolean {
        val batteryManager = context.getSystemService(Context.BATTERY_SERVICE) as BatteryManager
        return batteryManager.isCharging
    }

    fun isAirplaneModeOn() : Boolean {
        return Settings.Global.getInt(context.contentResolver, Settings.Global.AIRPLANE_MODE_ON, 0) != 0
    }
}