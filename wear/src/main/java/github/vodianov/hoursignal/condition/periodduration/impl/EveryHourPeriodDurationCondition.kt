package github.vodianov.hoursignal.condition.periodduration.impl

import android.util.Log
import github.vodianov.hoursignal.condition.periodduration.PeriodDurationCondition
import java.time.LocalDateTime

class
EveryHourPeriodDurationCondition : PeriodDurationCondition {

    private val logTag = "EveryHourPeriodDurationCondition"
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