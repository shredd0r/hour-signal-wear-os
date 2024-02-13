package github.vodianov.hoursignal.service.signal

import android.content.Context
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.PeriodicWorkRequest
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkInfo
import androidx.work.WorkManager
import github.vodianov.hoursignal.service.signal.worker.SignalWorker
import java.time.Duration

class SignalServiceImpl(context: Context) : SignalService {

    private val workName = "SignalWorkName"
    private val workManager = WorkManager.getInstance(context)

    override fun start() {
        workManager.enqueueUniquePeriodicWork(
            workName,
            ExistingPeriodicWorkPolicy.REPLACE,
            PeriodicWorkRequestBuilder<SignalWorker>(
                Duration.ofMinutes(PeriodicWorkRequest.MIN_PERIODIC_INTERVAL_MILLIS))
                .build())
    }

    override fun stop() {
        workManager.cancelUniqueWork(workName)
    }

    override fun isRunning(): Boolean {
        val workInfos = workManager.getWorkInfosForUniqueWork(workName).get()

        for (workInfo in workInfos) {
            return workInfo.state == WorkInfo.State.RUNNING || workInfo.state == WorkInfo.State.ENQUEUED
        }

        return false
    }
}