package github.vodianov.hoursignal.repository.settings

import android.content.Context
import android.content.res.AssetManager
import github.vodianov.hoursignal.dto.settings.Settings
import github.vodianov.hoursignal.repository.settings.SettingsRepository
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.decodeFromStream

class AssetsJsonSettingRepository(context: Context) : SettingsRepository {

    private val fileName = "settings.json"
    private val assets: AssetManager

    init {
        this.assets = context.assets
    }

    override fun save(settings: Settings) {
        TODO("Not yet implemented")
    }

    override fun get(): Settings {
        return Json.decodeFromStream<Settings>(assets.open(fileName))
    }
}