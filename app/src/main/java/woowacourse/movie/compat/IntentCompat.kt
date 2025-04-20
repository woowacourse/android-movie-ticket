package woowacourse.movie.compat

import android.content.Intent
import android.os.Build

object IntentCompat {
    fun <T> getParcelableExtra(
        intent: Intent,
        name: String,
        clazz: Class<T>,
    ): T? {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableExtra(name, clazz)
        } else {
            intent.getParcelableExtra(name)
        }
    }
}
