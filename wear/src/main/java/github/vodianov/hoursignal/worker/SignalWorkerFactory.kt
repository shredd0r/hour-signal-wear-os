package github.vodianov.hoursignal.worker

import android.content.Context
import androidx.work.ListenableWorker
import androidx.work.WorkerFactory
import androidx.work.WorkerParameters
import github.vodianov.hoursignal.workflow.SignalWorkflow

class SignalWorkerFactory(private val signalWorkflow: SignalWorkflow) : WorkerFactory() {
    override fun createWorker(
        appContext: Context,
        workerClassName: String,
        workerParameters: WorkerParameters
    ): ListenableWorker? {
        return SignalWorker(signalWorkflow, appContext, workerParameters)
    }
}