package woowacourse.movie.view.common

import android.content.Intent
import android.os.Build

fun <T> Intent.parcelableExtra(
    key: String,
    clazz: Class<T>,
): T? =
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        getParcelableExtra(key, clazz)
    } else {
        getParcelableExtra(key) as? T
    }
