package woowacourse.movie.domain

import java.time.LocalDateTime

class Movie(
    val id: Long,
    val title: String,
    val runningTime: Minute,
    val summary: String
) {
    val screenings: Screenings = Screenings()

    fun addScreening(screening: Screening) {
        screenings.addScreening(screening)
    }

    fun reserve(screeningDateTime: LocalDateTime, audienceCount: Int) {
        val screening = Screening(screeningDateTime)
        val reservationResult = ReservationResult(screeningDateTime, audienceCount)
        screenings.reserve(screening, reservationResult)
    }
}
