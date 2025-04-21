package woowacourse.movie.utils

import android.content.Intent
import android.os.Build
import android.os.Bundle
import java.io.Serializable

fun <T : Serializable> Intent.intentSerializable(
    key: String,
    clazz: Class<T>,
): T? =
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        getSerializableExtra(key, clazz)
    } else {
        getSerializableExtra(key) as T?
    }

fun <T : Serializable> Bundle.bundleSerializable(
    key: String,
    clazz: Class<T>,
): T? =
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        getSerializable(key, clazz)
    } else {
        getSerializable(key) as T?
    }
