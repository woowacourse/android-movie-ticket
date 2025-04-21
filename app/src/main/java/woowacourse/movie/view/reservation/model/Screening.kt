package woowacourse.movie.view.reservation.model

import android.os.Parcelable
import androidx.annotation.DrawableRes
import kotlinx.parcelize.Parcelize
import woowacourse.movie.domain.screening.DefaultShowtimePolicy
import woowacourse.movie.domain.screening.ShowtimePolicy
import woowacourse.movie.view.movie.model.Movie
import java.time.LocalDate
import java.time.LocalTime

@Parcelize
class Screening(
    private val movie: Movie,
    private val start: LocalDate,
    private val end: LocalDate,
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

    fun availableDates(now: LocalDate? = LocalDate.now()): List<LocalDate> = dates.filterNot { date -> date.isBefore(now) }

    fun showtimes(
        date: LocalDate,
        showTimePolicy: ShowtimePolicy = DefaultShowtimePolicy(),
    ): List<LocalTime> = showTimePolicy.showtimes(date)

    private val dates: List<LocalDate> =
        run {
            var currentDate = start
            val screeningDates = mutableListOf<LocalDate>()
            while (!currentDate.isAfter(end)) {
                screeningDates.add(currentDate)
                currentDate = currentDate.plusDays(1)
            }
            screeningDates
        }
}
