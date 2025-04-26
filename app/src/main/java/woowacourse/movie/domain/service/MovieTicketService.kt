package woowacourse.movie.domain.service

import woowacourse.movie.domain.model.MovieTicket
import java.time.LocalDateTime

class MovieTicketService {
    fun createMovieTicket(
        id: Int,
        screeningDateTime: LocalDateTime,
        headCount: Int,
    ): MovieTicket = MovieTicket(id, screeningDateTime, headCount)
}
