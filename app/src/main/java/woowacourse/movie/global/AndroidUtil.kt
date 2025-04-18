package woowacourse.movie.global

import android.content.Intent
import android.os.Build

inline fun <reified T> Intent.getObjectFromIntent(key: String): T {
    return if (Build.VERSION.SDK_INT >= 33) {
        getParcelableExtra(key, T::class.java) ?: throw IllegalStateException(ERR_OBJECT_NOT_FOUND)
    } else {
        @Suppress("DEPRECATION")
        getParcelableExtra(key) as? T ?: throw IllegalStateException(ERR_OBJECT_NOT_FOUND)
    }
}

const val ERR_OBJECT_NOT_FOUND = "객체를 찾을 수 없습니다."
