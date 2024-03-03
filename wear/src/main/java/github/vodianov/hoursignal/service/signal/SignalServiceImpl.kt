package github.vodianov.hoursignal.service.signal

import android.content.Context
import androidx.work.ExistingWorkPolicy
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkInfo
import androidx.work.WorkManager
import androidx.work.WorkRequest
import github.vodianov.hoursignal.service.signal.worker.SignalWorker

class SignalServiceImpl(private val context: Context) : SignalService {

    private val workTag = "SignalWorkTag"
    private val workName = "SignalWorkName"
    private lateinit var workManager: WorkManager

    override fun start() {
        nextSignalIteration()
    }

    override fun stop() {
        initWorkManager()

        workManager.cancelAllWorkByTag(workTag)
    }

    override fun isRunning(): Boolean {
        initWorkManager()

        val workInfos = workManager.getWorkInfosByTag(workTag).get()

        for (workInfo in workInfos) {
            return workInfo.state == WorkInfo.State.RUNNING || workInfo.state == WorkInfo.State.ENQUEUED
        }

        return false
    }

    override fun nextSignalIteration() {
        initWorkManager()

            workManager.enqueueUniqueWork(
                workName,
                ExistingWorkPolicy.REPLACE,
                OneTimeWorkRequestBuilder<SignalWorker>()
                    .addTag(workTag)
                    .build())
    }

    private fun initWorkManager() {
        if (::workManager.isInitialized)
            return

        workManager = WorkManager.getInstance(context)
    }
}