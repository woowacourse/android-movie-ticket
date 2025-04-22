package woowacourse.movie.ext

import android.content.Intent
import android.os.Build

inline fun <reified T> Intent.getSerializableCompat(key: String): T {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        getParcelableExtra(key, T::class.java) ?: throw IllegalArgumentException()
    } else {
        val value = getParcelableExtra(key) as? T
        if (value is T) {
            value
        } else {
            throw IllegalArgumentException()
        }
    }
}
