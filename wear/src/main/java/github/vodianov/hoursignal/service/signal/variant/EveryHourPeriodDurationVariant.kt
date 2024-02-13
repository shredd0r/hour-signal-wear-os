package github.vodianov.hoursignal.service.signal.variant

import android.media.MediaPlayer
import android.util.Log
import java.time.LocalDateTime
import java.util.concurrent.TimeUnit

class EveryHourPeriodDurationVariant : PeriodDurationVariant {

    private val sleepInSeconds = 5L
    private val logTag = "EveryHourPeriodDurationVariant"
    override suspend fun workflow(mediaPlayer: MediaPlayer) {
        var previousHour = LocalDateTime.now().hour

        while(true) {
            val now = LocalDateTime.now()

            if (previousHour != now.hour && now.minute == 0) {
                Log.d(logTag, "start mediaPlayer")
                mediaPlayer.start()
                previousHour = LocalDateTime.now().hour
                break
            }
            else {
                Thread.sleep(TimeUnit.SECONDS.toMillis(sleepInSeconds))
            }
        }
    }
}