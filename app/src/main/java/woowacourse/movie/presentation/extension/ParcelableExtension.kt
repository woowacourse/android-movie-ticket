package woowacourse.movie.presentation.extension

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.Parcelable

inline fun <reified T : Parcelable> Intent?.getParcelableCompat(key: String): T? =
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        this?.getParcelableExtra(key, T::class.java)
    } else {
        @Suppress("DEPRECATION")
        this?.getParcelableExtra(key) as? T
    }

inline fun <reified T : Parcelable> Bundle?.getParcelableCompat(key: String): T? =
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        this?.getParcelable(key, T::class.java)
    } else {
        @Suppress("DEPRECATION")
        this?.getParcelable(key) as? T
    }
