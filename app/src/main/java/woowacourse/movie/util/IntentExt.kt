package woowacourse.movie.util

import android.content.Intent
import android.os.Build
import java.io.Serializable

@Suppress("DEPRECATION")
inline fun <reified T : Serializable> Intent.customGetSerializable(
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
