package woowacourse.movie.domain.repository

import woowacourse.movie.domain.model.MovieTicket

interface MovieTicketRepository {
    fun setMovieTicket(
        title: String,
        screeningDate: String,
    )

    fun getMovieTicket(): MovieTicket

    fun updateReservationCount(movieTicket: MovieTicket)
}
