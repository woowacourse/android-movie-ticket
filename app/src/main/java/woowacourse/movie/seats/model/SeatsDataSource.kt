package woowacourse.movie.seats.model

object SeatsDataSource {
    var date: String = ""
    var time: String = ""
    var seatTotalPrice = 0
    val selectedSeats = mutableListOf<Seat>()
    var movieId: Long = -1
    var seat: Seat = Seat.of(1, 1)
}
