package github.vodianov.hoursignal.repository.valuestorage

import android.content.Context
import android.content.SharedPreferences
import android.util.Log

class SharedReferencesKeyValueRepositoryImpl(context: Context): KeyValueRepository {

    private val logTag = "SharedReferencesKeyValueRepositoryImpl"
    private val runningKey = "runningKey"
    private val preferenceName = "keyValuePreferences"
    private val sharedPreferences : SharedPreferences
    private val editor: SharedPreferences.Editor
    init {
        sharedPreferences = context.getSharedPreferences(preferenceName, Context.MODE_PRIVATE)
        editor = sharedPreferences.edit()
    }

    override fun isRunning(): Boolean {
        return sharedPreferences.getBoolean(runningKey, false)
    }

    override fun setIsRunning(isRunning: Boolean) {
        editor.putBoolean(runningKey, isRunning)
        editor.commit()
        Log.d(logTag, "saved new volume")
    }
}