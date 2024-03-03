package github.vodianov.hoursignal.condition.signaloff

import github.vodianov.hoursignal.condition.signaloff.impl.AirplaneModeEnableSignalOffCondition
import github.vodianov.hoursignal.condition.signaloff.impl.DNDEnableSignalOffCondition
import github.vodianov.hoursignal.condition.signaloff.impl.IsChargingSignalOffCondition

class SignalOffConditionFactory(
    private val isChargingSignalOffCondition: IsChargingSignalOffCondition,
    private val dndEnableSignalOffCondition: DNDEnableSignalOffCondition,
    private val airplaneModeEnableSignalOffCondition: AirplaneModeEnableSignalOffCondition
) {


    fun getAllCondition() : List<SignalOffCondition> {
        return listOf(
//            isChargingSignalOffCondition,
//            dndEnableSignalOffCondition,
            airplaneModeEnableSignalOffCondition
        )
    }
}