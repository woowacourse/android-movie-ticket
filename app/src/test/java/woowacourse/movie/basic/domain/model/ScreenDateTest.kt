package woowacourse.movie.basic.domain.model

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import woowacourse.movie.domain.model.ScreenDate
import java.time.LocalDate
import java.time.LocalTime

class ScreenDateTest {
    @Test
    fun `getLocalDateTime 함수는 제공된 시간으로 LocalDateTime을 반환해야 한다`() {
        // Given
        val date = LocalDate.of(2024, 4, 1)
        val time = LocalTime.of(10, 30)
        val screenDate = ScreenDate(date)

        // When
        val localDateTime = screenDate.getLocalDateTime(time)

        // Then
        assertEquals(date.atTime(time), localDateTime)
    }

    @Test
    fun `getSelectableTimes 함수는 주말일 때, weekendSelectableTimes를 반환해야 한다`() {
        // Given
        val weekendDate = LocalDate.of(2024, 4, 7)
        val screenDate = ScreenDate(weekendDate)

        // When
        val selectableTimes = screenDate.getSelectableTimes()

        // Then
        assertEquals(ScreenDate.weekendSelectableTimes, selectableTimes)
    }

    @Test
    fun `getSelectableTimes 함수는 주중일 때, weekdaySelectableTimes를 반환해야 한다`() {
        // Given
        val weekdayDate = LocalDate.of(2024, 4, 8)
        val screenDate = ScreenDate(weekdayDate)

        // When
        val selectableTimes = screenDate.getSelectableTimes()

        // Then
        assertEquals(ScreenDate.weekdaySelectableTimes, selectableTimes)
    }
}
