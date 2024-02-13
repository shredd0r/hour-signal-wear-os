package github.vodianov.hoursignal.dto.settings

import kotlinx.serialization.Serializable

@Serializable
data class Settings(val periodSignalRegion: PeriodSignalRegion,
                    val soundRegion: SoundRegion, )