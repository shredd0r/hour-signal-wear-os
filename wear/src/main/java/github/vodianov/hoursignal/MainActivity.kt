package github.vodianov.hoursignal

import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import github.vodianov.hoursignal.databinding.ActivityMainBinding
import github.vodianov.hoursignal.service.signal.SignalService
import github.vodianov.hoursignal.service.signal.SignalServiceImpl

class MainActivity : Activity() {

    private val logTag = "MainActivity"
    private lateinit var binding: ActivityMainBinding
    private lateinit var signalService: SignalService

    private fun initialize() {
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        signalService = SignalServiceImpl(this)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initialize()
        updateStateRunning()
    }

    fun onClickStartButton(view: View) {
        start()
    }
    fun onClickStopButton(view: View) {
        stop()
    }

    private fun start() {
        if (signalService.isRunning())
            Log.d(logTag, "service already started")
        else {
            signalService.start()
            Log.d(logTag, "service is start")
            updateStateRunning()
        }
    }

    private fun stop() {
        if (signalService.isRunning()) {
            signalService.stop()
            Log.d(logTag, "service is stopped")
            updateStateRunning()
        }
        else
            Log.d(logTag, "service already stopped")


    }

    private fun updateStateRunning() {
        val statusTextView = findViewById<TextView>(R.id.statusTextView)
        val newStatusText =
            if (signalService.isRunning())
                R.string.status_run
            else R.string.status_stop

        statusTextView.text = getString(newStatusText)
    }
}