package github.vodianov.hoursignal.condition.periodduration

import java.time.LocalDateTime

interface PeriodDurationCondition {
    fun isTimeForNewSignal(now: LocalDateTime) : Boolean
}