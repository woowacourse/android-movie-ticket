package woowacourse.movie.domain.model

import woowacourse.movie.domain.model.MovieTime.Companion.getMovieTimes

data class BookingInfo(
    val movie: Movie,
    private var date: MovieDate = movie.startDate,
    private var time: MovieTime = getMovieTimes(DateType.from(date)).first(),
    private val seats: MovieSeats = MovieSeats(),
    private val ticketCount: TicketCount = TicketCount(),
) {
    val selectedDate: MovieDate get() = date
    val selectedTime: MovieTime get() = time
    val selectedSeats: Set<MovieSeat> get() = seats.value
    val totalPrice: TicketPrice get() = seats.totalPrice
    val currentTicketCount: Int get() = ticketCount.value
    val isSeatAllSelected: Boolean get() = selectedSeats.size == currentTicketCount

    fun updateDate(date: MovieDate) {
        this.date = date
        time = getMovieTimes(DateType.from(date)).first()
    }

    fun updateMovieTime(movieTime: MovieTime) {
        time = movieTime
    }

    fun increaseTicketCount(count: Int = 1) {
        ticketCount.increase(count)
    }

    fun decreaseTicketCount(count: Int = 1) {
        ticketCount.decrease(count)
    }

    fun addSeat(seat: MovieSeat): SeatSelectionResult =
        if (ticketCount.value > selectedSeats.size) {
            seats.add(seat)
            SeatSelectionResult.Success(seat)
        } else {
            SeatSelectionResult.ExceedCountFailure
        }

    fun removeSeat(seat: MovieSeat) {
        seats.remove(seat)
    }

    fun updateSeat(seat: MovieSeat): SeatSelectionResult =
        if (seats.value.contains(seat)) {
            removeSeat(seat)
            SeatSelectionResult.Success(seat.copy(isSelected = false))
        } else {
            addSeat(seat.copy(isSelected = true))
        }
}
