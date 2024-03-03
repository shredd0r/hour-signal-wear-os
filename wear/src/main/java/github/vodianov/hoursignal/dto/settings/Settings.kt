package github.vodianov.hoursignal.dto.settings

import kotlinx.serialization.Serializable

@Serializable
data class Settings(val startAfterBoot: Boolean,
                    val periodSignalRegion: PeriodSignalRegion,
                    val soundRegion: SoundRegion,
                    val muteSignalRegion: MuteSignalRegion)