package woowacourse.movie.domain.repository

import woowacourse.movie.domain.model.MovieTicket
import java.time.LocalDateTime

interface MovieTicketRepository {
    fun createMovieTicket(
        movieTitle: String,
        screeningDate: LocalDateTime,
        reservationCount: Int,
    ): MovieTicket

    fun getMovieTicket(movieTicketId: Int): MovieTicket
}
