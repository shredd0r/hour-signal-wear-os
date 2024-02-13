package github.vodianov.hoursignal.service.signal.worker

import android.content.Context
import android.media.MediaPlayer
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import github.vodianov.hoursignal.dto.settings.Settings
import github.vodianov.hoursignal.repository.base.SettingsRepository
import github.vodianov.hoursignal.repository.base.SoundRepository
import github.vodianov.hoursignal.service.signal.factory.PeriodDurationVariantFactory

class SignalWorker(settingsRepository: SettingsRepository,
                   private val soundRepository: SoundRepository,
                   appContext: Context,
                   params: WorkerParameters) : CoroutineWorker(appContext, params) {
    private val mediaPlayer = MediaPlayer()
    private val settings : Settings = settingsRepository.get()
    private val logTag = "SignalWorker"

    init {
        initialMediaPlayer()
    }

    private fun initialMediaPlayer() {
        val sound = soundRepository.getSoundUriByName(settings.soundRegion.soundName)
        mediaPlayer.setDataSource(sound)
        mediaPlayer.prepare()
    }
    override suspend fun doWork(): Result {
        val periodDurationVariant = PeriodDurationVariantFactory.getVariantBy(settings)
        Log.d(logTag, "start period duration workflow")
        periodDurationVariant.workflow(mediaPlayer)

        return Result.success()
    }
}