package woowacourse.movie.domain.screening

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.time.LocalDate

class ScreeningDateTest {
    @Test
    fun `시작일과 종료일로 상영 일정을 만들 수 있다`() {
        val result = ScreeningDate(
            LocalDate.of(2024, 4, 1),
            LocalDate.of(2024, 4, 5)
        ).schedules()

        val expected = ScreeningSchedule(
            listOf(
                DailySchedule(LocalDate.of(2024, 4, 1)),
                DailySchedule(LocalDate.of(2024, 4, 2)),
                DailySchedule(LocalDate.of(2024, 4, 3)),
                DailySchedule(LocalDate.of(2024, 4, 4)),
                DailySchedule(LocalDate.of(2024, 4, 5)),
            )
        )
        assertThat(result).isEqualTo(expected)
    }
}
