package woowacourse.movie.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import woowacourse.movie.model.MovieDate.Companion.isWeekend
import java.time.LocalDate

class MovieDateTest {
    @Test
    fun `영화 상영 날짜의 시작일은 종료일보다 이전이어야 한다`() {
        assertThrows<IllegalArgumentException> {
            MovieDate(
                LocalDate.of(2024, 4, 3),
                LocalDate.of(2024, 4, 1),
            )
        }
    }

    @Test
    fun `영화 상영 날짜 시작일~종료일 까지의 날짜 리스트를 생성한다`() {
        val movieDate = MovieDate(LocalDate.of(2024, 4, 1), LocalDate.of(2024, 4, 3))

        val expect =
            listOf(LocalDate.of(2024, 4, 1), LocalDate.of(2024, 4, 2), LocalDate.of(2024, 4, 3))

        assertThat(movieDate.generateDates()).isEqualTo(expect)
    }

    @Test
    fun `영화 상영 날짜가 평일인지 확인 가능하다`() {
        assertThat(isWeekend(LocalDate.of(2024, 4, 1))).isFalse()
    }

    @Test
    fun `영화 상영 날짜가 주말인지 확인 가능하다`() {
        assertThat(isWeekend(LocalDate.of(2024, 4, 7))).isTrue()
    }
}
