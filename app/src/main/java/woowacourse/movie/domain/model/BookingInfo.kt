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

    fun addSeat(seat: MovieSeat) {
        if (ticketCount.value > movieSeats.seats.size) {
            movieSeats.add(seat)
        }
    }

    fun addSeats(seats: Set<MovieSeat>) {
        if (ticketCount.value > movieSeats.seats.size) {
            movieSeats.addAll(seats)
        }
    }

    fun removeSeat(seat: MovieSeat) {
        movieSeats.remove(seat)
    }
}
