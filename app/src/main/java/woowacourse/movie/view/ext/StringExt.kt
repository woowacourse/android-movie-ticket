package woowacourse.movie.view.ext

import android.content.Context
import java.time.LocalDate

fun String.toDrawableResourceId(context: Context): Int {
    return context.resources.getIdentifier(this, "drawable", context.packageName)
}

fun String.toLocalDateFromDotFormat(): LocalDate {
    val (year, month, day) = this.split(".").map { it.toInt() }
    return LocalDate.of(year, month, day)
}
