package woowacourse.movie.global

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Parcelable
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

inline fun <reified T> Intent.getObjectFromIntent(key: String): T {
    return if (Build.VERSION.SDK_INT >= 33) {
        getParcelableExtra(key, T::class.java) ?: throw IllegalStateException(ERR_OBJECT_NOT_FOUND)
    } else {
        @Suppress("DEPRECATION")
        getParcelableExtra(key) as? T ?: throw IllegalStateException(ERR_OBJECT_NOT_FOUND)
    }
}

inline fun <reified T : Activity> Context.newIntent(data: List<Pair<String, Parcelable>> = listOf()): Intent {
    return Intent(this, T::class.java)
        .apply { data.forEach { (key, value) -> putExtra(key, value) } }
}

fun LocalDateTime.toFormattedString(): String {
    return DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm").format(this)
}

fun LocalDateTime.toFormattedDate(): String {
    return DateTimeFormatter.ofPattern("yyyy.MM.dd").format(this)
}

fun LocalDate.toFormattedString(): String {
    return DateTimeFormatter.ofPattern("MM.dd").format(this)
}

const val ERR_OBJECT_NOT_FOUND = "객체를 찾을 수 없습니다."
