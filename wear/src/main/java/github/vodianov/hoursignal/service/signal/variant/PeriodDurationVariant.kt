package github.vodianov.hoursignal.service.signal.variant

import android.media.MediaPlayer

interface PeriodDurationVariant {
    suspend fun workflow(mediaPlayer: MediaPlayer)
}