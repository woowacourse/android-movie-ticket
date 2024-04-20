package woowacourse.movie.domain.repository

import woowacourse.movie.domain.model.MovieTicket

interface MovieTicketRepository {
    fun setMovieTicket(
        title: String,
        screeningDate: String,
        reservationCount: Int,
    )

    fun getMovieTicket(): MovieTicket

    fun updateMovieTicket(movieTicket: MovieTicket)
}
