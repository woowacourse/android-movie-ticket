package woowacourse.movie.domain

import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource
import woowacourse.movie.R
import java.time.LocalDate

class MovieTest {
    @ParameterizedTest
    @ValueSource(ints = [16, 17, 20, 30])
    fun `상영 시작일은 종료일과 같거나 이전이어야 한다`(dayOfMonth: Int) {
        val startDate = LocalDate.of(2025, 4, 16)
        val endDate = LocalDate.of(2025, 4, dayOfMonth)
        assertDoesNotThrow {
            Movie(
                title = "해리 포터와 마법사의 돌",
                startDate = startDate,
                endDate = endDate,
                runningTime = 152,
                poster = R.drawable.harry_potter_poster,
            )
        }
    }

    @ParameterizedTest
    @ValueSource(ints = [1, 2, 10, 15])
    fun `상영 시작일이 종료일보다 늦을 수 없다`(dayOfMonth: Int) {
        val startDate = LocalDate.of(2025, 4, 16)
        val endDate = LocalDate.of(2025, 4, dayOfMonth)
        assertThrows<IllegalArgumentException> {
            Movie(
                title = "해리 포터와 마법사의 돌",
                startDate = startDate,
                endDate = endDate,
                runningTime = 152,
                poster = R.drawable.harry_potter_poster,
            )
        }
    }
}
