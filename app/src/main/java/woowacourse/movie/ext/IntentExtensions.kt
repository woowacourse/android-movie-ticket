package woowacourse.movie.ext

import android.content.Intent
import android.os.Build

inline fun <reified T> Intent.getSerializableCompat(key: String): T {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        getParcelableExtra(key, T::class.java) ?: throw IllegalArgumentException(ERROR_NO_EXTRA_DATA)
    } else {
        val value = getParcelableExtra(key) as? T
        if (value is T) {
            value
        } else {
            throw IllegalArgumentException(ERROR_NO_EXTRA_DATA)
        }
    }
}

const val ERROR_NO_EXTRA_DATA = "해당 부가 데이터를 찾을 수 없습니다"
