package woowacourse.movie.ticket

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import woowacourse.movie.domain.movies.MovieToReserve
import woowacourse.movie.domain.seat.Seat
import woowacourse.movie.domain.ticket.MovieTicket
import woowacourse.movie.domain.ticket.MovieTicketService
import java.time.LocalDateTime

class MovieTicketServiceTest {
    private lateinit var movieTicketService: MovieTicketService

    @BeforeEach
    fun setUp() {
        movieTicketService = MovieTicketService()
    }

    @Test
    fun `가격 정책에 맞는 티켓을 발행한다`() {
        val movieToReserve = MovieToReserve(1, LocalDateTime.of(2025, 4, 22, 10, 0), 2)
        val amount = 1
        val selectedSeats = listOf<Seat>()
        val result = movieTicketService.createMovieTicket(movieToReserve, amount, selectedSeats)

        result shouldBe MovieTicket(1, LocalDateTime.of(2025, 4, 22, 10, 0), 2, 1, listOf<Seat>())
    }
}
