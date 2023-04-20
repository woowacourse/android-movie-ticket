package woowacourse.movie.model

data class Seat(
    val location: Location,
    val grade: SeatGrade,
) {
    fun getPrice() = grade.ticketPrice
}
