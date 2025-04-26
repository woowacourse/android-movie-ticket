package woowacourse.movie.domain.model

import woowacourse.movie.domain.model.MovieTime.Companion.getMovieTimes

data class BookingInfo(
    val movie: Movie,
) {
    private var _date: MovieDate = movie.startDate
    val date: MovieDate get() = _date

    private var _movieTime: MovieTime = getMovieTimes(DateType.from(date)).first()
    val movieTime: MovieTime get() = _movieTime

    val ticketCount: TicketCount = TicketCount()

    private val movieSeats: MovieSeats = MovieSeats()
    val selectedSeats: Set<MovieSeat> get() = movieSeats.seats

    val totalPrice: TicketPrice get() = movieSeats.totalPrice

    val isSeatAllSelected: Boolean get() = selectedSeats.size == ticketCount.value

    fun updateDate(date: MovieDate) {
        _date = date
        _movieTime = getMovieTimes(DateType.from(date)).first()
    }

    fun updateMovieTime(movieTime: MovieTime) {
        _movieTime = movieTime
    }

    fun increaseTicketCount(count: Int = 1) {
        ticketCount.increase(count)
    }

    fun decreaseTicketCount(count: Int = 1) {
        ticketCount.decrease(count)
    }

    fun addSeat(seat: MovieSeat): SeatSelectionResult =
        if (ticketCount.value > selectedSeats.size) {
            movieSeats.add(seat)
            SeatSelectionResult.Success(seat)
        } else {
            SeatSelectionResult.ExceedCountFailure
        }

    fun addSeats(seats: Set<MovieSeat>) {
        if (ticketCount.value > selectedSeats.size + seats.size) {
            movieSeats.addAll(seats)
        }
    }

    fun removeSeat(seat: MovieSeat) {
        movieSeats.remove(seat)
    }

    fun updateSeat(seat: MovieSeat): SeatSelectionResult =
        if (movieSeats.seats.contains(seat)) {
            removeSeat(seat)
            SeatSelectionResult.Success(seat.copy(isSelected = false))
        } else {
            addSeat(seat.copy(isSelected = true))
        }
}
