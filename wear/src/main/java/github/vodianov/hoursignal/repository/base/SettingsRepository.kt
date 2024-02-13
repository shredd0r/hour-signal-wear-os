package github.vodianov.hoursignal.repository.base

import github.vodianov.hoursignal.dto.settings.Settings

interface SettingsRepository {

    fun save(settings: Settings)
    fun get(): Settings
}