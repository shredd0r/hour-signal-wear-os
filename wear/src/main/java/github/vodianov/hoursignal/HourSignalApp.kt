package github.vodianov.hoursignal

import android.app.Application
import androidx.work.Configuration
import androidx.work.WorkManager
import dagger.hilt.android.HiltAndroidApp
import github.vodianov.hoursignal.repository.base.SettingsRepository
import github.vodianov.hoursignal.repository.base.SoundRepository
import github.vodianov.hoursignal.service.signal.SignalService
import github.vodianov.hoursignal.service.signal.worker.SignalWorkerFactory
import javax.inject.Inject

@HiltAndroidApp
class HourSignalApp : Application(), Configuration.Provider {
    @Inject lateinit var settingsRepository: SettingsRepository
    @Inject lateinit var soundRepository: SoundRepository
    @Inject lateinit var signalService: SignalService

    override fun onCreate() {
        super.onCreate()
        initializeWorkManager()
    }

    private fun initializeWorkManager() {
        WorkManager.initialize(this, workManagerConfiguration)
    }

    override fun getWorkManagerConfiguration(): Configuration {
        return Configuration.Builder()
            .setWorkerFactory(
                SignalWorkerFactory(
                    settingsRepository,
                    soundRepository,
                    signalService))
            .build()
    }
}