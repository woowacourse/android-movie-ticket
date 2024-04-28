package woowacourse.movie.domain

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import woowacourse.movie.domain.DateMaker
import java.time.LocalDate
import java.time.LocalDateTime

class DateMakerTest {
    private val dateMaker: DateMaker = DateMaker()

    @Test
    fun `getDatesBetween 메서드가 주어진 시작일과 종료일 사이의 날짜를 올바르게 반환하는지 확인한다`() {
        // Given
        val startDate = LocalDate.of(2024, 4, 1)
        val endDate = LocalDate.of(2024, 4, 5)
        val expectedDates =
            listOf(
                LocalDate.of(2024, 4, 1),
                LocalDate.of(2024, 4, 2),
                LocalDate.of(2024, 4, 3),
                LocalDate.of(2024, 4, 4),
                LocalDate.of(2024, 4, 5),
            )

        // When
        val dates = dateMaker.getDatesBetween(startDate, endDate)

        // Then
        assertEquals(expectedDates, dates)
    }

    @Test
    fun `getDateTimes 메서드가 올바른 날짜에 대한 상영 시간을 올바르게 반환하는지 확인한다`() {
        // Given
        val date = LocalDate.of(2024, 4, 1)
        val expectedTimes =
            listOf(
                LocalDateTime.of(2024, 4, 1, 10, 0),
                LocalDateTime.of(2024, 4, 1, 12, 0),
                LocalDateTime.of(2024, 4, 1, 14, 0),
                LocalDateTime.of(2024, 4, 1, 16, 0),
                LocalDateTime.of(2024, 4, 1, 18, 0),
                LocalDateTime.of(2024, 4, 1, 20, 0),
                LocalDateTime.of(2024, 4, 1, 22, 0),
            )

        // When
        val times = dateMaker.getDateTimes(date)

        // Then
        assertEquals(expectedTimes, times)
    }
}
