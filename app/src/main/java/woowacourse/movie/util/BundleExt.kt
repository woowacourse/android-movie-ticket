package woowacourse.movie.util

import android.os.Build
import android.os.Bundle
import android.os.Parcelable
import java.io.Serializable

@Suppress("DEPRECATION")
inline fun <reified T : Parcelable> Bundle.customGetParcelable(
    key: String,
    failedProcess: (key: String) -> Unit = {}
): T? {
    val result = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        getParcelable(key, T::class.java)
    } else {
        getParcelable(key) as? T
    }
    result?.let { return it }
    failedProcess(key)
    return null
}

@Suppress("DEPRECATION")
inline fun <reified T : Serializable> Bundle.customGetSerializable(
    key: String,
    failedProcess: (key: String) -> Unit = {}
): T? {
    val result = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        getSerializable(key, T::class.java)
    } else {
        getSerializable(key) as? T
    }
    result?.let { return it }
    failedProcess(key)
    return null
}
