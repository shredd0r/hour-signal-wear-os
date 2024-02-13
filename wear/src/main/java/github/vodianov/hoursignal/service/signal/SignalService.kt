package github.vodianov.hoursignal.service.signal

interface SignalService {
    fun start()
    fun stop()
    fun isRunning() : Boolean
}