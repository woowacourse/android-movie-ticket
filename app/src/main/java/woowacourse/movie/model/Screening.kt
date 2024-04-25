package woowacourse.movie.model

import java.time.LocalDate

class Screening private constructor(
    val screeningId: Long,
    val movieId: Long,
    val theater: Theater,
    val datePeriod: DatePeriod,
) {
    val dates: List<String>
        get() {
            var currentDate: LocalDate = datePeriod.startDate
            return mutableListOf<String>().apply {
                do {
                    add(currentDate.toString())
                    currentDate = currentDate.plusDays(datePeriod.dateSpan)
                } while (currentDate <= datePeriod.endDate)
            }
        }

    companion object {
        fun of(
            screeningId: Long,
            movieId: Long,
            datePeriod: DatePeriod,
        ): Screening =
            Screening(
                screeningId,
                movieId,
                Theater.of(),
                datePeriod,
            )
    }
}
