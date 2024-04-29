package woowacourse.movie.model.screening

import woowacourse.movie.model.MovieData
import woowacourse.movie.model.Result
import woowacourse.movie.model.theater.Theater
import java.time.LocalDate

class Screening private constructor(
    val screeningId: Long,
    val movie: Movie,
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
        private const val ERROR_INVALID_SCREENING_ID = "존재하지 않는 상영 정보입니다."

        fun of(
            screeningId: Long,
            movieId: Long,
            datePeriod: DatePeriod,
        ): Screening {
            return when (val movie = MovieData.findMovieById(movieId)) {
                is Result.Success ->
                    Screening(
                        screeningId,
                        movie.data,
                        Theater.of(),
                        datePeriod,
                    )
                is Result.Error -> throw IllegalArgumentException(ERROR_INVALID_SCREENING_ID)
            }
        }
    }
}
