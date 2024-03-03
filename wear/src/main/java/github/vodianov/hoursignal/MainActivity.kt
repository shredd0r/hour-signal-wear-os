package github.vodianov.hoursignal

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.activity.ComponentActivity
import dagger.hilt.android.AndroidEntryPoint
import github.vodianov.hoursignal.databinding.ActivityMainBinding
import github.vodianov.hoursignal.service.signal.SignalService
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val logTag = "MainActivity"
    private lateinit var binding: ActivityMainBinding
    @Inject lateinit var signalService: SignalService

    private fun initialize() {
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
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