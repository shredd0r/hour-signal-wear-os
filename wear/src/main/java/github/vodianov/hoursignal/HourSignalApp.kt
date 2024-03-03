package github.vodianov.hoursignal

import android.app.Application
import androidx.work.Configuration
import androidx.work.WorkManager
import dagger.hilt.android.HiltAndroidApp
import github.vodianov.hoursignal.repository.base.SettingsRepository
import github.vodianov.hoursignal.repository.base.SoundRepository
import github.vodianov.hoursignal.service.DeviceInfoService
import github.vodianov.hoursignal.service.signal.SignalService
import github.vodianov.hoursignal.worker.SignalWorkerFactory
import github.vodianov.hoursignal.workflow.SignalWorkflow
import javax.inject.Inject

@HiltAndroidApp
class HourSignalApp : Application(), Configuration.Provider {
    @Inject lateinit var signalWorkflow: SignalWorkflow

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
                SignalWorkerFactory(signalWorkflow))
            .build()
    }
}