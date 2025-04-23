package woowacourse.movie.mapper

import android.content.Intent
import android.os.Build
import android.os.Parcelable

object IntentCompat {
    @Suppress("DEPRECATION")
    fun <T : Parcelable> getParcelableExtra(intent: Intent, key: String, clazz: Class<T>): T? {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableExtra(key, clazz)
        } else {
            intent.getParcelableExtra(key) as? T
        }
    }
}