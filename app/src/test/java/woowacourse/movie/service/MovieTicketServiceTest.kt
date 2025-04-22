package woowacourse.movie.service

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import woowacourse.movie.domain.model.MovieTicket
import woowacourse.movie.domain.policy.DefaultPricingPolicy
import woowacourse.movie.domain.service.MovieTicketService
import java.time.LocalDateTime

class MovieTicketServiceTest {
    private lateinit var movieTicketService: MovieTicketService

    @BeforeEach
    fun setUp() {
        movieTicketService = MovieTicketService(DefaultPricingPolicy())
    }

    @Test
    fun `가격 정책에 맞는 티켓을 발행한다`() {
        val movieId = "match"
        val screeningDate = LocalDateTime.of(2025, 4, 22, 10, 0)
        val headCount = 2
        val result = movieTicketService.createMovieTicket(movieId, screeningDate, headCount)

        result shouldBe MovieTicket("match", LocalDateTime.of(2025, 4, 22, 10, 0), 2, 26000)
    }
}
