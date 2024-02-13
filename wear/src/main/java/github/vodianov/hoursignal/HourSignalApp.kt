package github.vodianov.hoursignal

import android.app.Application
import androidx.work.Configuration
import androidx.work.WorkManager
import github.vodianov.hoursignal.repository.base.SettingsRepository
import github.vodianov.hoursignal.repository.base.SoundRepository
import github.vodianov.hoursignal.repository.impl.AssetsJsonSettingRepository
import github.vodianov.hoursignal.repository.impl.AssetsSoundRepository
import github.vodianov.hoursignal.service.signal.worker.SignalWorkerFactory

class HourSignalApp : Application() {
    private lateinit var settingsRepository: SettingsRepository
    private lateinit var soundRepository: SoundRepository

    override fun onCreate() {
        super.onCreate()
        initialize()
        initializeWorkManager()
    }

    private fun initialize() {
        settingsRepository = AssetsJsonSettingRepository(this)
        soundRepository = AssetsSoundRepository(this)
    }

    private fun initializeWorkManager() {
        WorkManager.initialize(
            this,
            Configuration.Builder()
                .setWorkerFactory(
                    SignalWorkerFactory(
                    settingsRepository,
                    soundRepository)
                )
                .build())
    }
}