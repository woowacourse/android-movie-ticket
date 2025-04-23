package woowacourse.movie.view.extension

import android.content.Intent
import android.os.Build
import java.io.Serializable

inline fun <reified T : Serializable> Intent.getSerializableExtraData(key: String): T =
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        requireNotNull(getSerializableExtra(key, T::class.java))
    } else {
        requireNotNull(getSerializableExtra(key) as? T)
    }
