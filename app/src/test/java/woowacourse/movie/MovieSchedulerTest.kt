package woowacourse.movie

import io.kotest.assertions.assertSoftly
import io.kotest.inspectors.forAll
import io.kotest.matchers.collections.shouldNotBeEmpty
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import woowacourse.movie.model.MovieScheduler
import java.time.LocalDate
import java.time.LocalTime

class MovieSchedulerTest {
    private lateinit var movieScheduler: MovieScheduler

    @BeforeEach
    fun setUp() {
        movieScheduler = MovieScheduler(
            LocalDate.of(2025, 4, 16),
            LocalDate.of(2025, 4, 30)
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
    fun `현재 이후의 예매 가능한 시간들을 반환한다`() {
        // When
        val bookableTimes = movieScheduler.getBookableTimes(LocalDate.now())

        // Then
        assertSoftly(bookableTimes) {
            shouldNotBeEmpty()
            forAll { bookableTime ->
                !bookableTime.isBefore(LocalTime.now()) shouldBe true
            }
        }
    }
}