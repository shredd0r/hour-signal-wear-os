package github.vodianov.hoursignal.dto.settings

import kotlinx.serialization.Serializable

@Serializable
class MuteSignalRegion(
    val muteWhenCharging: Boolean,
    val muteWhenDNDOn: Boolean,
    val muteWhenAirplaneModeOn: Boolean) {
}