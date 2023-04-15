package woowacourse.movie.extensions

import android.os.Build
import android.os.Bundle
import java.io.Serializable

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
