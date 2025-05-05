package woowacourse.movie.domain.ticket

import woowacourse.movie.domain.movies.MovieToReserve
import woowacourse.movie.domain.seat.Seat

class MovieTicketService {
    fun createMovieTicket(
        movieToReserve: MovieToReserve,
        amount: Int,
        selectedSeats: List<Seat>,
    ): MovieTicket =
        MovieTicket(
            movieToReserve.movieId,
            movieToReserve.screeningDateTime,
            movieToReserve.headCount,
            amount,
            selectedSeats,
        )
}
