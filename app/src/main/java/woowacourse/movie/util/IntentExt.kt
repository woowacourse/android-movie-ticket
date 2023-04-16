package woowacourse.movie.util

import android.content.Intent
import android.os.Build
import android.os.Parcelable
import java.io.Serializable

@Suppress("DEPRECATION")
inline fun <reified T : Parcelable> Intent.customGetParcelableExtra(
    key: String,
    failedProcess: (key: String) -> Unit = {}
): T? {
    val result = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        getParcelableExtra(key, T::class.java)
    } else {
        getParcelableExtra(key) as? T
    }
    result?.let { return it }
    failedProcess(key)
    return null
}

@Suppress("DEPRECATION")
inline fun <reified T : Serializable> Intent.customGetSerializableExtra(
    key: String,
    failedProcess: (key: String) -> Unit = {}
): T? {
    val result = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        getSerializableExtra(key, T::class.java)
    } else {
        getSerializableExtra(key) as? T
    }
    result?.let { return it }
    failedProcess(key)
    return null
}
