package woowacourse.movie.domain.reservation

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.time.LocalDate
import java.time.LocalDateTime

class ScreeningTest {
    private lateinit var screening: Screening

    @BeforeEach
    fun setUp() {
        screening =
            Screening(
                Movie(
                    0,
                    "해리 포터와 마법사의 돌",
                    152,
                ),
                start = LocalDate.of(2025, 4, 1),
                end = LocalDate.of(2025, 4, 5),
                current = LocalDateTime.of(2025, 4, 3, 12, 0),
            )
    }

    @Test
    fun `상영일 중 가능한 날짜의 목록을 얻을 수 있다`() {
        // when
        val availableDates = screening.availableDates()

        // then
        assertThat(availableDates)
            .isEqualTo(
                listOf(
                    LocalDate.of(2025, 4, 3),
                    LocalDate.of(2025, 4, 4),
                    LocalDate.of(2025, 4, 5),
                ),
            )
    }
}
