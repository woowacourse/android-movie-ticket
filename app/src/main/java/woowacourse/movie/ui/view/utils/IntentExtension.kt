package woowacourse.movie.ui.view.utils

import android.content.Intent
import android.os.Build
import java.io.Serializable

@Suppress("UNCHECKED_CAST")
fun <T : Serializable> Intent.intentSerializable(
    key: String,
    customClass: Class<T>,
): T =
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        this.getSerializableExtra(key, customClass)!!
    } else {
        this.getSerializableExtra(key) as T
    }
