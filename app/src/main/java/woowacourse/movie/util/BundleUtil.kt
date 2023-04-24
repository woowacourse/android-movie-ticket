package woowacourse.movie.util

import android.os.Build
import android.os.Bundle
import java.io.Serializable

@Suppress("DEPRECATION")
inline fun <reified T : Serializable> Bundle.getSerializableExtraByKey(key: String): T? {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        getSerializable(key, T::class.java)
    } else {
        getSerializable(key) as? T
    }
}
