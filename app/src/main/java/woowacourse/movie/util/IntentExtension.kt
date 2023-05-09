package woowacourse.movie.util

import android.content.Intent
import android.os.Build
import android.os.Parcelable

inline fun <reified T : Parcelable> Intent.parcelable(key: String): T = when {
    Build.VERSION.SDK_INT >= 33 -> getParcelableExtra(key, T::class.java)
        ?: throw IllegalArgumentException()
    else -> @Suppress("DEPRECATION")
    getParcelableExtra(key) as? T
        ?: throw IllegalArgumentException()
}
