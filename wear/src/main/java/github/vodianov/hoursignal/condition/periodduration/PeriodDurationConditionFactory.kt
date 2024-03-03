package github.vodianov.hoursignal.condition.periodduration

import github.vodianov.hoursignal.condition.periodduration.impl.EveryHourPeriodDurationCondition
import github.vodianov.hoursignal.dto.settings.Settings
import github.vodianov.hoursignal.enums.PeriodDurationType

class PeriodDurationConditionFactory {

    companion object {
        fun getConditionBy(type: PeriodDurationType): PeriodDurationCondition {
            return when(type) {
                PeriodDurationType.EVERY_HOUR -> EveryHourPeriodDurationCondition()
                PeriodDurationType.CUSTOM -> TODO("Not yet implement")
            }
        }
        fun getConditionBy(settings: Settings): PeriodDurationCondition {
            return getConditionBy(getType(settings))
        }

        private fun getType(settings: Settings) : PeriodDurationType {
            if (settings.periodSignalRegion.everyHour == true)
                return PeriodDurationType.EVERY_HOUR
            return PeriodDurationType.CUSTOM
        }
    }
}