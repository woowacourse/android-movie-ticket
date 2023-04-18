package woowacourse.movie.util

import android.content.Intent
import android.os.Build
import java.io.Serializable

inline fun <reified T : Serializable> Intent.getSerializable(key: String, data: Class<T>): T {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        getSerializableExtra(key, data) ?: throw IllegalArgumentException()
    } else {
        getSerializableExtra(key) as T? ?: throw IllegalArgumentException()
    }
}

fun <T> Intent.getParcelable(key: String, data: Class<T>): T {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        getParcelableExtra(key, data) ?: throw IllegalArgumentException()
    } else {
        getParcelableExtra(key) as T? ?: throw IllegalArgumentException()
    }
}
