package woowacourse.movie.common

import android.content.Intent
import android.os.Build
import android.os.Bundle

fun <T> Intent.getParcelableExtraCompat(
    key: String,
    clazz: Class<T>,
): T? =
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        getParcelableExtra(key, clazz)
    } else {
        getParcelableExtra(key) as? T
    }

fun <T> Bundle.getParcelableCompat(
    key: String,
    clazz: Class<T>,
): T? =
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        getParcelable(key, clazz)
    } else {
        getParcelable(key) as? T
    }
