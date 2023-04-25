package woowacourse.movie.util

import android.content.Intent
import android.os.Build
import android.os.Build.VERSION.SDK_INT
import android.os.Parcelable

const val ERROR_MESSAGE = "데이터가 넘어오지 않았습니다."

@Suppress("DEPRECATION")
inline fun <reified T : java.io.Serializable>
Intent.customGetSerializable(key: String): T {
    return if (SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        getSerializableExtra(key, T::class.java) ?: throw IllegalArgumentException(ERROR_MESSAGE)
    } else {
        getSerializableExtra(key) as T
    }
}

inline fun <reified T : Parcelable> Intent.parcelable(key: String): T = when {
    SDK_INT >= Build.VERSION_CODES.TIRAMISU -> getParcelableExtra(key, T::class.java) ?: throw IllegalArgumentException(ERROR_MESSAGE)
    else -> @Suppress("DEPRECATION") getParcelableExtra(key) as? T ?: throw IllegalArgumentException(ERROR_MESSAGE)
}
