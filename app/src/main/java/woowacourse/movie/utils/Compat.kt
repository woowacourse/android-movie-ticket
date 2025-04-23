package woowacourse.movie.utils

import android.content.Intent
import android.os.Build
import android.os.Parcelable

fun <T : Parcelable> Intent.parcelableCompat(
    key: String,
    type: Class<T>,
): T {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        this.getParcelableExtra(key, type) ?: throw IllegalArgumentException("[ERROR] ${key}에 대한 데이터를 찾을 수 없습니다.")
    } else {
        @Suppress("DEPRECATION")
        this.getParcelableExtra(key) ?: throw IllegalArgumentException("[ERROR] ${key}에 대한 데이터를 찾을 수 없습니다.")
    }
}
