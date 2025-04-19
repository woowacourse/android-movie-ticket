package woowacourse.movie.data

import io.kotest.assertions.assertSoftly
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import woowacourse.movie.model.data.MovieTicket
import woowacourse.movie.model.policy.DefaultPricingPolicy
import java.time.LocalDateTime

class MovieTicketTest {
    private lateinit var movieTicket: MovieTicket

    @BeforeEach
    fun setUp() {
        movieTicket =
            MovieTicket(
                "승부",
                LocalDateTime.of(2025, 4, 16, 11, 0),
                3,
                DefaultPricingPolicy(),
            )
    }

    @Test
    fun `제목, 상영날짜, 인원을 가진다`() {
        // Then
        assertSoftly(movieTicket) {
            title shouldBe "승부"
            screeningDateTime shouldBe LocalDateTime.of(2025, 4, 16, 11, 0)
            headCount shouldBe 3
        }
    }

    @Test
    fun `예매 수량에 따른 총 결제 금액을 가진다`() {
        // Then
        movieTicket.amount shouldBe 3 * 13000
    }
}
