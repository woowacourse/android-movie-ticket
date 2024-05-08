package woowacourse.movie

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import woowacourse.movie.MainActivity.Companion.ACTION_CUSTOM

class MyReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        when (intent?.action) {
            Intent.ACTION_SCREEN_ON -> Log.d("james", "ACTION_SCREEN_ON")
            Intent.ACTION_SCREEN_OFF -> Log.d("james", "ACTION_SCREEN_OFF")
            ACTION_CUSTOM -> Log.d("james", "ACTION_CUSTOM")
        }
    }
}

