package woowacourse.movie.domain.model

data class Seats(val seats: List<Seat>) {
    override fun toString(): String {
        return seats.joinToString(", ") { it.toString() }
    }

    fun totalPrice() = seats.sumOf { it.seatGrade.price }
}
