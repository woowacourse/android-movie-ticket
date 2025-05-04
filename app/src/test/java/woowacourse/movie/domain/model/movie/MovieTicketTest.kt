package woowacourse.movie.domain.model.movie

import io.kotest.assertions.assertSoftly
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.time.LocalDateTime

class MovieTicketTest {
    private lateinit var movieTicket: MovieTicket

    @BeforeEach
    fun setUp() {
        movieTicket =
            MovieTicket(
                "승부",
                LocalDateTime.of(2025, 5, 1, 11, 0),
                3,
            )
    }

    @Test
    fun `제목, 상영날짜, 인원을 가진다`() {
        // Then
        assertSoftly(movieTicket) {
            title shouldBe "승부"
            screeningDateTime shouldBe LocalDateTime.of(2025, 5, 1, 11, 0)
            headCount shouldBe 3
        }
    }

    @Test
    fun `이전 시간의 영화를 예매할 수 없다`() {
        // Given
        val beforeTime = LocalDateTime.of(2025, 4, 1, 12, 0)

        // Then
        shouldThrow<IllegalArgumentException> {
            MovieTicket(
                "Test",
                beforeTime,
                2,
            )
        }
    }
}
