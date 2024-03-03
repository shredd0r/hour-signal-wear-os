package github.vodianov.hoursignal.condition.signaloff

import github.vodianov.hoursignal.dto.settings.Settings

interface SignalOffCondition {

    fun signalIsIgnored(settings: Settings) : Boolean
}