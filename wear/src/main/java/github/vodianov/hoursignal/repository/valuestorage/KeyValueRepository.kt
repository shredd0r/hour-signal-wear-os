package github.vodianov.hoursignal.repository.valuestorage

interface KeyValueRepository {

    fun isRunning() : Boolean
    fun setIsRunning(boolean: Boolean)
}