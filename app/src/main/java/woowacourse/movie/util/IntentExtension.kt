package woowacourse.movie.util

import android.content.Intent
import android.os.Build
import android.os.Bundle
import java.io.Serializable

inline fun <reified T : Serializable> Intent.getSerializable(key: String, data: Class<T>): T {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        getSerializableExtra(key, data) ?: throw IllegalArgumentException()
    } else {
        getSerializableExtra(key) as T? ?: throw IllegalArgumentException()
    }
}

fun <T> Intent.getParcelable(key: String, data: Class<T>): T? {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        getParcelableExtra(key, data)
    } else {
        getParcelableExtra(key) as T?
    }
}

fun <T> Bundle.getParcelableBundle(key: String, data: Class<T>): T {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        getParcelable(key, data) ?: throw IllegalArgumentException()
    } else {
        getParcelable(key) as T? ?: throw IllegalArgumentException()
    }
}
