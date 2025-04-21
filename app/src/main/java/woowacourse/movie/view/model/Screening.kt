package woowacourse.movie.view.model

import android.os.Parcelable
import androidx.annotation.DrawableRes
import kotlinx.parcelize.Parcelize
import woowacourse.movie.domain.DefaultShowtimePolicy
import woowacourse.movie.domain.ShowtimePolicy
import java.time.LocalDate
import java.time.LocalTime

@Parcelize
class Screening(
    private val movie: Movie,
    val start: LocalDate,
    val end: LocalDate,
) : Parcelable {
    @DrawableRes
    val posterId: Int = movie.posterId
    val title: String = movie.title
    val runningTime: Int = movie.runningTime

    val startYear: Int = start.year
    val startMonth: Int = start.monthValue
    val startDay: Int = start.dayOfMonth

    val endYear: Int = end.year
    val endMonth: Int = end.monthValue
    val endDay: Int = end.dayOfMonth

    val dates: List<LocalDate> =
        run {
            var currentDate = start
            val screeningDates = mutableListOf<LocalDate>()
            while (!currentDate.isAfter(end)) {
                screeningDates.add(currentDate)
                currentDate = currentDate.plusDays(1)
            }
            screeningDates
        }

    fun showtimes(
        date: LocalDate,
        showTimePolicy: ShowtimePolicy = DefaultShowtimePolicy(),
    ): List<LocalTime> = showTimePolicy.showtimes(date)
}
