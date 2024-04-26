package woowacourse.movie.model.screening

import woowacourse.movie.model.MovieData
import woowacourse.movie.model.theater.Theater
import java.time.LocalDate

class Screening private constructor(
    val screeningId: Long,
    val movie: Movie?,
    val theater: Theater,
    val datePeriod: DatePeriod,
) {
    val dates: List<LocalDate>
        get() {
            var currentDate: LocalDate = datePeriod.startDate
            return mutableListOf<LocalDate>().apply {
                do {
                    add(currentDate)
                    currentDate = currentDate.plusDays(datePeriod.dateSpan)
                } while (currentDate <= datePeriod.endDate)
            }
        }

    companion object {
        fun of(
            screeningId: Long,
            movieId: Long,
            datePeriod: DatePeriod,
        ): Screening {
            val movie = MovieData.findMovieById2(movieId)
            return Screening(
                screeningId,
                movie.data,
                Theater.of(),
                datePeriod,
            )
        }
    }
}
