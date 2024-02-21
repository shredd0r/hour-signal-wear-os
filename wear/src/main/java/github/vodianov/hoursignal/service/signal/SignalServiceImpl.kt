package github.vodianov.hoursignal.service.signal

import android.content.Context
import androidx.work.ExistingWorkPolicy
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkInfo
import androidx.work.WorkManager
import github.vodianov.hoursignal.service.signal.worker.SignalWorker

class SignalServiceImpl(context: Context) : SignalService {

    private val workName = "SignalWorkName"
    private val workManager = WorkManager.getInstance(context)

    override fun start() {
        workManager.enqueueUniqueWork(
            workName,
            ExistingWorkPolicy.REPLACE,
            OneTimeWorkRequestBuilder<SignalWorker>().build())
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