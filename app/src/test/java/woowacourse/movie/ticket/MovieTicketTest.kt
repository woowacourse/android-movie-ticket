package woowacourse.movie.ticket

import io.kotest.assertions.assertSoftly
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import woowacourse.movie.domain.seat.Seat
import woowacourse.movie.domain.ticket.MovieTicket
import java.time.LocalDateTime

class MovieTicketTest {
    private lateinit var movieTicket: MovieTicket

    @BeforeEach
    fun setUp() {
        movieTicket =
            MovieTicket(
                1,
                LocalDateTime.of(2025, 4, 16, 11, 0),
                2,
                20000,
                listOf(Seat(0, 0), Seat(0, 1)),
            )
    }

    @Test
    fun `id, 상영날짜, 인원을 가진다`() {
        // Then
        assertSoftly(movieTicket) {
            movieId shouldBe 1
            screeningDateTime shouldBe LocalDateTime.of(2025, 4, 16, 11, 0)
            headCount shouldBe 2
            amount shouldBe 20000
            selectedSeats shouldBe listOf(Seat(0, 0), Seat(0, 1))
        }
    }
}
