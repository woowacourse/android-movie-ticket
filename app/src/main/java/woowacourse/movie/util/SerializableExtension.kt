package woowacourse.movie.util

import android.content.Intent
import android.os.Build
import android.os.Bundle
import java.io.Serializable

inline fun <reified T : Serializable> Intent.intentSerializable(
    key: String,
    clazz: Class<T>,
): T? {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        this.getSerializableExtra(key, clazz)
    } else {
        this.getSerializableExtra(key) as T?
    }
}

inline fun <reified T : Serializable> Bundle.bundleSerializable(
    key: String,
    clazz: Class<T>,
): T? {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        this.getSerializable(key, clazz)
    } else {
        this.getSerializable(key) as T?
    }
}
