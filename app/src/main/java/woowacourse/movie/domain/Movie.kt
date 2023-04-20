package woowacourse.movie.domain

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

    fun reserve(screening: Screening, reservationResult: ReservationResult) {
        screenings.reserve(screening, reservationResult)
    }
}
