package woowacourse.movie.domain.repository

import woowacourse.movie.domain.model.MovieTicket
import java.time.LocalDate

interface MovieTicketRepository {
    fun createMovieTicket(
        movieTitle: String,
        screeningDate: LocalDate,
    ): MovieTicket

    fun getMovieTicket(movieTicketId: Int): MovieTicket

    fun updateReservationCount(
        movieTicketId: Int,
        count: Int,
    )
}
