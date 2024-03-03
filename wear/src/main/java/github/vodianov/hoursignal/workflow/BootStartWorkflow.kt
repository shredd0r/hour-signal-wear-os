package github.vodianov.hoursignal.workflow

import android.util.Log
import github.vodianov.hoursignal.repository.settings.SettingsRepository
import github.vodianov.hoursignal.repository.valuestorage.KeyValueRepository
import github.vodianov.hoursignal.service.signal.SignalService
import kotlin.math.log

class BootStartWorkflow(
    private val signalService: SignalService,
    private val settingsRepository: SettingsRepository,
    private val keyValueRepository: KeyValueRepository,
) {

    private val logTag = "BootStartWorkflow"
    fun start() {
        if (keyValueRepository.isRunning()) {
            Log.d(logTag, String.format("is running: %s"))
            return
        }

        val settings = settingsRepository.get()

        if (settings.startAfterBoot) {
            signalService.start()
            Log.d(logTag, "start")
        }
    }
}