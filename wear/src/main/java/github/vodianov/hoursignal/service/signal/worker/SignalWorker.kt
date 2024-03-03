package github.vodianov.hoursignal.service.signal.worker

import android.content.Context
import android.media.MediaPlayer
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import github.vodianov.hoursignal.dto.settings.Settings
import github.vodianov.hoursignal.repository.base.SettingsRepository
import github.vodianov.hoursignal.repository.base.SoundRepository
import github.vodianov.hoursignal.service.signal.SignalService
import github.vodianov.hoursignal.service.signal.factory.PeriodDurationVariantFactory
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import java.time.LocalDateTime
import java.util.concurrent.TimeUnit


class SignalWorker (settingsRepository: SettingsRepository,
                   private val soundRepository: SoundRepository,
                   private val signalService: SignalService,
                   appContext: Context,
                   params: WorkerParameters) : CoroutineWorker(appContext, params) {
    private val mediaPlayer = MediaPlayer()
    private val settings : Settings = settingsRepository.get()
    private val logTag = "SignalWorker"
    private val sleepInSeconds = 5L

    init {
        Log.d(logTag, "init new instance")
        initialMediaPlayer()
    }

    private fun initialMediaPlayer() {
        val sound = soundRepository.getSoundUriByName(settings.soundRegion.soundName)
        mediaPlayer.setDataSource(sound)
        mediaPlayer.prepare()
    }
    override suspend fun doWork(): Result = coroutineScope {
        val periodDurationVariant = PeriodDurationVariantFactory.getVariantBy(settings)

        while(isActive) {
            if (periodDurationVariant.isTimeForNewSignal(LocalDateTime.now())) {
                Log.d(logTag, "start mediaPlayer")
                mediaPlayer.start()
                break
            }
            else {
                Log.d(logTag, "start sleep")
                delay(TimeUnit.SECONDS.toMillis(sleepInSeconds))
            }

        }

        signalService.nextSignalIteration()
        Log.d(logTag, "added new worker")

        return@coroutineScope Result.success()
    }
}