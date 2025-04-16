package woowacourse.movie

import io.kotest.assertions.assertSoftly
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import woowacourse.movie.model.BookedMovie
import java.time.LocalDate

class BookedMovieTest {
    @Test
    fun `제목과 상영일을 가진다`() {
        // When
        val bookedMovie = BookedMovie("승부", LocalDate.of(2025, 3, 26))

        // Then
        assertSoftly(bookedMovie) {
            title shouldBe "승부"
            screeningDate shouldBe LocalDate.of(2025, 3, 26)
        }
    }
}
