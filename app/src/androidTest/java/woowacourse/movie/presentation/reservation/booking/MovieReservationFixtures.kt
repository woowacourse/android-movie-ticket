package woowacourse.movie.presentation.reservation.booking

import woowacourse.movie.data.FakeMovieRepository
import java.time.format.DateTimeFormatter

private const val DATE_FORMAT = "yyyy.MM.dd"
private const val TIME_FORMAT = "HH:mm"
private val DateFormatter = DateTimeFormatter.ofPattern(DATE_FORMAT)
private val TimeFormatter = DateTimeFormatter.ofPattern(TIME_FORMAT)

fun selectedDate(position: Int): String {
    val screenMovie = FakeMovieRepository().screenMovies().first()
    val date = screenMovie.screenDateTimes[position].date
    return date.format(DateFormatter)
}

fun selectedTime(
    datePosition: Int = 0,
    timePosition: Int,
): String {
    val screenMovie = FakeMovieRepository().screenMovies().first()
    val time = screenMovie.screenDateTimes[datePosition].times[timePosition]
    return time.format(TimeFormatter)
}
