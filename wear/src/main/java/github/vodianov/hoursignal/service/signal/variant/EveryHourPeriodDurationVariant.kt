package github.vodianov.hoursignal.service.signal.variant

import android.util.Log
import java.time.LocalDateTime
import java.util.concurrent.TimeUnit

class
EveryHourPeriodDurationVariant : PeriodDurationVariant {

    private val logTag = "EveryHourPeriodDurationVariant"
    private var previousHour = LocalDateTime.now().hour
    override fun isTimeForNewSignal(now: LocalDateTime): Boolean {
        if (previousHour != now.hour && now.minute == 0) {
            Log.d(logTag, "time to new signal")
            return true
        }

        Log.d(logTag, "not yet time for new signal")
        return false
    }
}