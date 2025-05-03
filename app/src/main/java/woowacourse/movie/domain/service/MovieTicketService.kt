package woowacourse.movie.domain.service

import woowacourse.movie.domain.model.MovieTicket
import woowacourse.movie.domain.model.ReservedMovie
import woowacourse.movie.domain.model.Seat

class MovieTicketService {
    fun createMovieTicket(
        reservedMovie: ReservedMovie,
        amount: Int,
        selectedSeats: List<Seat>,
    ): MovieTicket =
        MovieTicket(
            reservedMovie.movieId,
            reservedMovie.screeningDateTime,
            reservedMovie.headCount,
            amount,
            selectedSeats,
        )
}
