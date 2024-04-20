package woowacourse.movie.domain.repository

import woowacourse.movie.domain.model.MovieTicket

object InMemoryMovieTicketRepository : MovieTicketRepository {
    private var movieTicket: MovieTicket? = null

    override fun setMovieTicket(
        title: String,
        screeningDate: String,
        reservationCount: Int,
    ) {
        movieTicket = MovieTicket(title, screeningDate, reservationCount)
    }

    override fun getMovieTicket(): MovieTicket {
        return movieTicket ?: throw IllegalStateException("예매 정보가 없습니다.")
    }

    override fun updateMovieTicket(movieTicket: MovieTicket) {
        InMemoryMovieTicketRepository.movieTicket = movieTicket
    }
}
