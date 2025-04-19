package woowacourse.movie.schedule

import io.kotest.assertions.assertSoftly
import io.kotest.inspectors.forAll
import io.kotest.matchers.collections.shouldNotBeEmpty
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import woowacourse.movie.model.schedule.MovieScheduler
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

class MovieSchedulerTest {
    private lateinit var movieScheduler: MovieScheduler

    @BeforeEach
    fun setUp() {
        movieScheduler =
            MovieScheduler(
                LocalDate.of(2025, 4, 16),
                LocalDate.of(2025, 4, 30),
            )
    }

    @Test
    fun `영화 상영 기간에 포함되며, 현재 이후의 예매 가능한 날짜들을 반환한다`() {
        // When
        val bookableDates = movieScheduler.getBookableDates()

        // Then
        assertSoftly(bookableDates) {
            shouldNotBeEmpty()
            forAll { bookableDate ->
                !bookableDate.isBefore(LocalDate.now()) shouldBe true
            }
        }
    }

    @Test
    fun `주말 19시 일 때 예매 가능 시간은 21시와 23시다`() {
        // When
        val today = LocalDate.of(2025, 4, 19)
        val bookableTimes =
            movieScheduler.getBookableTimes(today, LocalDateTime.of(today, LocalTime.of(19, 0)))

        // Then
        bookableTimes shouldBe listOf(LocalTime.of(21, 0), LocalTime.of(23, 0))
    }
}
