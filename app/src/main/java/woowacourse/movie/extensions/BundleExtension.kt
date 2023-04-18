package woowacourse.movie.extensions

import android.os.Build
import android.os.Bundle
import android.os.Parcelable

inline fun <reified T : Parcelable> Bundle.getParcelableCompat(key: String): T? =
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        this.get(key)
        getParcelable(key, T::class.java)
    } else {
        getParcelable(key) as? T
    }
