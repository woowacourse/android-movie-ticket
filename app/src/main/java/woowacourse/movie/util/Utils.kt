package woowacourse.movie.util

import android.content.Intent
import android.os.Build
import woowacourse.movie.domain.Movie
import java.time.LocalDate

fun <T> Intent.parcelableExtraWithVersion(
    key: String,
    clazz: Class<T>,
): T? =
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        getParcelableExtra(key, clazz)
    } else {
        getParcelableExtra(key) as? T
    }

fun Movie.dateRange(stepDays: Long): List<LocalDate> {
    val dates = mutableListOf<LocalDate>()
    var current = startDate

    while (!current.isAfter(endDate)) {
        dates.add(current)
        current = current.plusDays(stepDays)
    }
    return dates
}
