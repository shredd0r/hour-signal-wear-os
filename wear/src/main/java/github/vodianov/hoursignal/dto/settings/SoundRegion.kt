package github.vodianov.hoursignal.dto.settings

import kotlinx.serialization.Serializable

@Serializable
data class SoundRegion(val vibration: String,
                       val soundName: String,
                       val volume: Double)