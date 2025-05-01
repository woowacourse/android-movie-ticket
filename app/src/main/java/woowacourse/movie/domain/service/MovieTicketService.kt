package woowacourse.movie.domain.service

import woowacourse.movie.domain.model.MovieTicket
import woowacourse.movie.domain.model.ReservedMovie

class MovieTicketService {
    fun createMovieTicket(reservedMovie: ReservedMovie): MovieTicket =
        MovieTicket(reservedMovie.movieId, reservedMovie.screeningDateTime, reservedMovie.headCount)
}
