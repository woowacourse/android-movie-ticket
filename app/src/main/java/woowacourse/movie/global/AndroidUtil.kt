package woowacourse.movie.global

import android.app.Activity
import android.content.Intent
import android.os.Build
import android.os.Parcelable

inline fun <reified T> Intent.getObjectFromIntent(key: String): T {
    return if (Build.VERSION.SDK_INT >= 33) {
        getParcelableExtra(key, T::class.java) ?: throw IllegalStateException(ERR_OBJECT_NOT_FOUND)
    } else {
        @Suppress("DEPRECATION")
        getParcelableExtra(key) as? T ?: throw IllegalStateException(ERR_OBJECT_NOT_FOUND)
    }
}

inline fun <reified T : Activity> Activity.newIntent(data: List<Pair<String, Parcelable>> = listOf()): Intent {
    return Intent(this, T::class.java)
        .apply { data.forEach { (key, value) -> putExtra(key, value) } }
}

const val ERR_OBJECT_NOT_FOUND = "객체를 찾을 수 없습니다."
