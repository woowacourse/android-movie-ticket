package woowacourse.movie.schedule

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import woowacourse.movie.model.schedule.DayType
import java.time.LocalDate

class DayTypeTest {
    @Test
    fun `날짜가 월요일과 금요일 사이에 해당하는 경우 WEEKDAY를 반환한다`() {
        // Given
        val date = LocalDate.of(2025, 4, 16)

        // When
        val result = DayType.from(date)

        // Then
        result shouldBe DayType.WEEKDAY
    }

    @Test
    fun `날짜가 토요일 또는 일요일인 경우 WEEKLY를 반환한다`() {
        // Given
        val date = LocalDate.of(2025, 4, 13)

        // When
        val result = DayType.from(date)

        // Then
        result shouldBe DayType.WEEKEND
    }
}
