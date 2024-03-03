package github.vodianov.hoursignal.condition.signaloff.impl

import github.vodianov.hoursignal.condition.signaloff.SignalOffCondition
import github.vodianov.hoursignal.dto.settings.Settings
import github.vodianov.hoursignal.service.DeviceInfoService

class IsChargingSignalOffCondition(private val deviceInfoService: DeviceInfoService) : SignalOffCondition {

    override fun signalIsIgnored(settings: Settings): Boolean {
        return settings.muteSignalRegion.muteWhenCharging && deviceInfoService.isCharging()
    }
}