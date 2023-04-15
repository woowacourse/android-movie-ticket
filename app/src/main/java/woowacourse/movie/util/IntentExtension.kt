package woowacourse.movie.util

import android.content.Intent
import android.os.Build

fun <T> Intent.getParcelable(key: String, data: Class<T>): T {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        getParcelableExtra(key, data) ?: throw IllegalArgumentException()
    } else {
        getParcelableExtra(key) ?: throw IllegalArgumentException()
    }
}
