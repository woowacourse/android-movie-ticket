package woowacourse.movie.domain.model.tools.seat

data class Seat(
    val location: Location,
    val grade: SeatGrade,
) : Comparable<Seat> {
    fun getPrice() = grade.ticketPrice
    override fun compareTo(other: Seat): Int {
        return compareValuesBy(this, other, { it.location.row }, { it.location.number })
    }
}
