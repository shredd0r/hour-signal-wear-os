package github.vodianov.hoursignal.service.signal.worker

import android.content.Context
import androidx.work.ListenableWorker
import androidx.work.WorkerFactory
import androidx.work.WorkerParameters
import github.vodianov.hoursignal.repository.base.SettingsRepository
import github.vodianov.hoursignal.repository.base.SoundRepository

class SignalWorkerFactory(private val settingsRepository: SettingsRepository,
                          private val soundRepository: SoundRepository) : WorkerFactory() {
    override fun createWorker(
        appContext: Context,
        workerClassName: String,
        workerParameters: WorkerParameters
    ): ListenableWorker? {
        return SignalWorker(settingsRepository, soundRepository, appContext, workerParameters)
    }
}