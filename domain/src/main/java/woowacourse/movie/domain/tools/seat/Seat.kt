package woowacourse.movie.domain.tools.seat

data class Seat(
    val location: Location,
    val grade: SeatGrade,
) {
    fun getPrice() = grade.ticketPrice
}
