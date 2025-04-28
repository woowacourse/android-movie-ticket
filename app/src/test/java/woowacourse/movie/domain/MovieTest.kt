package woowacourse.movie.domain

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import woowacourse.movie.fixture.DomainFixture
import java.time.LocalDate

class MovieTest {
    private lateinit var movie: MovieListData.Movie

    @BeforeEach
    fun setUp() {
        movie = DomainFixture.movie
    }

    @Test
    fun `각 영화의 상영일은 각자의 범위를 갖는다(예시 2025_4_1 ~ 2025_4_25)`() {
        val betweenDates = movie.betweenDates(LocalDate.of(2025, 4, 3))
        assertThat(betweenDates).isEqualTo(
            listOf(
                LocalDate.of(2025, 4, 3),
                LocalDate.of(2025, 4, 4),
                LocalDate.of(2025, 4, 5),
            ),
        )
    }

    @Test
    fun `이미 상영이 끝난 범위를 가질 수 없다`() {
        assertThatThrownBy {
            movie.betweenDates(LocalDate.of(2025, 4, 6))
        }.isInstanceOf(IllegalStateException::class.java)
            .hasMessage("이미 상영이 종료된 영화입니다.")
    }
}
