package woowacourse.movie

import android.annotation.SuppressLint
import android.content.Intent
import android.content.IntentFilter
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private lateinit var myReceiver: MyReceiver

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupReceiver()
        setupButton()
    }

    private fun sendCustomBroadcast() {
        val intent = Intent()
        intent.action = ACTION_CUSTOM
        intent.putExtra("name", "james")
        sendBroadcast(intent)
    }

    @SuppressLint("UnspecifiedRegisterReceiverFlag")
    private fun setupReceiver() {
        myReceiver = MyReceiver()
        val filter = IntentFilter().apply {
            addAction(Intent.ACTION_SCREEN_ON)
            addAction(Intent.ACTION_SCREEN_OFF)
            addAction(ACTION_CUSTOM)
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            registerReceiver(myReceiver, filter, RECEIVER_NOT_EXPORTED)
        } else {
            registerReceiver(myReceiver, filter)
        }
    }

    private fun setupButton() {
        findViewById<Button>(R.id.sendButton).setOnClickListener {
            sendCustomBroadcast()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(myReceiver)
    }

    companion object {
        const val ACTION_CUSTOM = "techcourse.action.CUSTOM"
    }
}
