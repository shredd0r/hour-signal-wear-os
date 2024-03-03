package github.vodianov.hoursignal.broadcast

import android.annotation.SuppressLint
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import dagger.hilt.android.AndroidEntryPoint
import github.vodianov.hoursignal.workflow.BootStartWorkflow
import javax.inject.Inject

@AndroidEntryPoint
class BootStartReceiver: BroadcastReceiver() {

    @Inject lateinit var bootStartWorkflow: BootStartWorkflow
    @SuppressLint("UnsafeProtectedBroadcastReceiver")
    override fun onReceive(context: Context?, intent: Intent) {
        if (intent.action == Intent.ACTION_BOOT_COMPLETED) {
            bootStartWorkflow.start()
        }
    }
}