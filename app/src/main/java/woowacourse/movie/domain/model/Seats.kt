package woowacourse.movie.domain.model

class Seats(val seats: List<Seat>) {
    fun register(seat: Seat): Seats = Seats(seats + seat)

    fun cancel(seat: Seat): Seats = Seats(seats - seat)
}
