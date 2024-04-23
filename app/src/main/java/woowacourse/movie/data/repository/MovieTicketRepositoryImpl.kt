package woowacourse.movie.data.repository

import woowacourse.movie.data.utils.IdGenerator
import woowacourse.movie.domain.model.MovieTicket
import woowacourse.movie.domain.repository.MovieTicketRepository
import java.time.LocalDate

object MovieTicketRepositoryImpl : MovieTicketRepository {
    private val tickets = mutableMapOf<Int, MovieTicket>()
    private const val ERROR_EMPTY_TICKET = "예매 정보가 없습니다."

    override fun createMovieTicket(
        movieTitle: String,
        screeningDate: LocalDate,
    ): MovieTicket {
        val id = IdGenerator.generateId()
        val newTicket = MovieTicket(id, movieTitle, screeningDate)
        tickets[id] = newTicket
        return newTicket
    }

    override fun getMovieTicket(movieTicketId: Int): MovieTicket = tickets[movieTicketId] ?: throw IllegalStateException(ERROR_EMPTY_TICKET)

    override fun updateReservationCount(
        movieTicketId: Int,
        count: Int,
    ) {
        val ticket = tickets[movieTicketId] ?: throw IllegalStateException(ERROR_EMPTY_TICKET)
        ticket.updateCount(count)
    }
}
