package woowacourse.movie.domain.repository

import woowacourse.movie.domain.model.MovieTicket
import java.time.LocalDate

interface MovieTicketRepository {
    fun createMovieTicket(
        movieTitle: String,
        screeningDate: LocalDate,
        reservationCount: Int,
    ): MovieTicket

    fun getMovieTicket(movieTicketId: Int): MovieTicket
}
