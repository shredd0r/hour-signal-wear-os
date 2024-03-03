package github.vodianov.hoursignal.worker

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import github.vodianov.hoursignal.workflow.SignalWorkflow


class SignalWorker (private val signalWorkflow: SignalWorkflow,
                    appContext: Context,
                    params: WorkerParameters
) : CoroutineWorker(appContext, params) {

    override suspend fun doWork(): Result {
        signalWorkflow.start()
        return Result.success()
    }
}