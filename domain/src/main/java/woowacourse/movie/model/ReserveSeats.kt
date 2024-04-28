package woowacourse.movie.model

class ReserveSeats(val seats: List<Seat>) {
    val totalPrice = Price(seats.sumOf { it.price })

    init {
        require(seats.distinct().size == seats.size) {
            "중복된 좌석이 들어올 수 없습니다."
        }
    }
}
