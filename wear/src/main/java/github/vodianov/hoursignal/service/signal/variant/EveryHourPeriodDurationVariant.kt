package github.vodianov.hoursignal.service.signal.variant

import android.media.MediaPlayer
import android.util.Log
import java.time.LocalDateTime
import java.util.concurrent.TimeUnit

class
EveryHourPeriodDurationVariant : PeriodDurationVariant {

    private val sleepInSeconds = 5L
    private var previousHour = LocalDateTime.now().hour
    private val logTag = "EveryHourPeriodDurationVariant"
    override suspend fun workflow(mediaPlayer: MediaPlayer) {
        val now = LocalDateTime.now()

        if (previousHour != now.hour && now.minute == 0) {
            Log.d(logTag, "start mediaPlayer")

            mediaPlayer.start()
            previousHour = now.hour
            Log.d(logTag, "worker end after sound")
        }
        else {
            Log.d(logTag, String.format("start sleep"))
            Thread.sleep(TimeUnit.SECONDS.toMillis(sleepInSeconds))
        }
    }
}