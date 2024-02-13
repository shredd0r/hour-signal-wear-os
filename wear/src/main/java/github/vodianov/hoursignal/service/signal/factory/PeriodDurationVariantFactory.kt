package github.vodianov.hoursignal.service.signal.factory

import github.vodianov.hoursignal.dto.settings.Settings
import github.vodianov.hoursignal.enums.PeriodDurationType
import github.vodianov.hoursignal.service.signal.variant.PeriodDurationVariant
import github.vodianov.hoursignal.service.signal.variant.EveryHourPeriodDurationVariant

class PeriodDurationVariantFactory {

    companion object {
        fun getVariantBy(type: PeriodDurationType): PeriodDurationVariant {
            return when(type) {
                PeriodDurationType.EVERY_HOUR -> EveryHourPeriodDurationVariant()
                PeriodDurationType.CUSTOM -> TODO("Not yet implement")
            }
        }
        fun getVariantBy(settings: Settings): PeriodDurationVariant {
            return getVariantBy(getType(settings))
        }

        private fun getType(settings: Settings) : PeriodDurationType {
            if (settings.periodSignalRegion.everyHour == true)
                return PeriodDurationType.EVERY_HOUR
            return PeriodDurationType.CUSTOM
        }
    }





}