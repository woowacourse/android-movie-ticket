package woowacourse.movie.view

import android.content.Intent
import android.os.Build
import android.os.Parcelable

inline fun <reified T : Parcelable> Intent.getParcelableExtraCompat(key: String): T? =
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        getParcelableExtra(key, T::class.java)
            ?: throw IllegalArgumentException(ERROR_NO_EXTRA_DATA.format(key))
    } else {
        @Suppress("DEPRECATION")
        getParcelableExtra(key) as? T
    }

const val ERROR_NO_EXTRA_DATA = "%s 데이터를 찾을 수 없습니다."
