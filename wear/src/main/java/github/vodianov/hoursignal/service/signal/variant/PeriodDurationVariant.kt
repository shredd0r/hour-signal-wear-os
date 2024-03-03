package github.vodianov.hoursignal.service.signal.variant

import java.time.LocalDateTime

interface PeriodDurationVariant {
    fun isTimeForNewSignal(now: LocalDateTime) : Boolean
}