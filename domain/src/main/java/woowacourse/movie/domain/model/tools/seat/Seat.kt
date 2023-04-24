package woowacourse.movie.domain.model.tools.seat

data class Seat(
    val location: Location,
    val grade: SeatGrade,
) : Comparable<Seat> {
    fun getPrice() = grade.ticketPrice
    override fun compareTo(other: Seat): Int {
        val comparedRow = this.location.row.toString().compareTo(other.location.row.toString())
        if (comparedRow != 0) return comparedRow

        return location.number.toString().compareTo(other.location.number.toString())
    }
}
