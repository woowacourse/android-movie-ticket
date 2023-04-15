package woowacourse.movie.util

import android.content.Intent
import android.os.Build

@Suppress("DEPRECATION")
inline fun <reified T : java.io.Serializable>
Intent.getVersionDependentSerializableExtra(key: String): T? {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        getSerializableExtra(key, T::class.java)
    } else {
        getSerializableExtra(key) as T
    }
}
