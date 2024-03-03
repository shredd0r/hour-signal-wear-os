package github.vodianov.hoursignal.workflow

import android.media.MediaPlayer
import android.util.Log
import github.vodianov.hoursignal.condition.signaloff.SignalOffConditionFactory
import github.vodianov.hoursignal.dto.settings.Settings
import github.vodianov.hoursignal.repository.base.SettingsRepository
import github.vodianov.hoursignal.repository.base.SoundRepository
import github.vodianov.hoursignal.service.DeviceInfoService
import github.vodianov.hoursignal.service.signal.SignalService
import github.vodianov.hoursignal.condition.periodduration.PeriodDurationConditionFactory
import github.vodianov.hoursignal.condition.signaloff.SignalOffCondition
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import java.time.LocalDateTime
import java.util.concurrent.TimeUnit


class SignalWorkflow(settingsRepository: SettingsRepository,
                     private val soundRepository: SoundRepository,
                     private val signalService: SignalService,
                     private val deviceInfoService: DeviceInfoService,
                     private val signalOffConditionFactory: SignalOffConditionFactory) {

    private val logTag = "SignalWorkflow"
    private val sleepInSeconds = 5L
    private val mediaPlayer = MediaPlayer()
    private val settings : Settings = settingsRepository.get()

    init {
        Log.d(logTag, "init new instance")
        initialMediaPlayer()
    }

    private fun initialMediaPlayer() {
        val sound = soundRepository.getSoundUriByName(settings.soundRegion.soundName)
        mediaPlayer.setDataSource(sound)
        mediaPlayer.prepare()
    }

    suspend fun start() = coroutineScope {
        val periodDurationCondition = PeriodDurationConditionFactory.getConditionBy(settings)
        val signalOffConditions = signalOffConditionFactory.getAllCondition()

        while(isActive) {
            if (periodDurationCondition.isTimeForNewSignal(LocalDateTime.now()) &&
                isSignalOn(signalOffConditions)) {

                Log.d(logTag, "start mediaPlayer")
                mediaPlayer.start()
                break
            }

            Log.d(logTag, "start sleep")
            delay(TimeUnit.SECONDS.toMillis(sleepInSeconds))
        }

        signalService.nextSignalIteration()
        Log.d(logTag, "added new worker")
    }

    private fun isSignalOn(signalOffConditions: List<SignalOffCondition>) : Boolean {
        for (signalOffCondition in signalOffConditions) {
            if (signalOffCondition.signalIsIgnored(settings)) {
                return false
            }
        }

        return true
    }
}