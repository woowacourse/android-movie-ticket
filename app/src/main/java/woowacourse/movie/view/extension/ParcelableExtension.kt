package woowacourse.movie.view.extension

import android.content.Intent
import android.os.Build

inline fun <reified T> Intent.getParcelableCompat(key: String): T =
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        this.getParcelableExtra(key, T::class.java) ?: throw IllegalArgumentException()
    } else {
        @Suppress("DEPRECATION")
        getParcelableExtra(key) as? T ?: throw IllegalArgumentException()
    }
