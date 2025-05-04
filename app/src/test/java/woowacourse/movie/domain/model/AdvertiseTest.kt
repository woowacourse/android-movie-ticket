package woowacourse.movie.domain.model

import io.kotest.assertions.assertSoftly
import io.kotest.matchers.shouldBe
import io.kotest.matchers.types.shouldBeTypeOf
import org.junit.jupiter.api.Test
import woowacourse.movie.domain.model.movie.Movie
import woowacourse.movie.presentation.movies.MoviesItem
import java.time.LocalDate

class AdvertiseTest {
    @Test
    fun `광고는 3개의 영화마다 1개씩 삽입된다`() {
        // Given
        val movies: List<Movie> = List(6) { Movie(
            "승부",
            LocalDate.of(2025, 3, 26),
            LocalDate.of(2025, 5, 26),
            115,
        ) }
        val advertise = Advertise(movies)

        // When
        val items = advertise.insertAdvertisement()

        // Then
        assertSoftly(items) {
            size shouldBe 8
            it[3].shouldBeTypeOf<MoviesItem.AdvertisementItem>()
            it[7].shouldBeTypeOf<MoviesItem.AdvertisementItem>()
        }
    }
}