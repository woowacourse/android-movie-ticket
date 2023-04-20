package woowacourse.movie.domain

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.time.LocalDate
import java.time.LocalTime

class TimesGeneratorTest {
    @Test
    fun `날짜가 평일이면 9시부터 23시까지 2시간 간격의 시간 리스트를 가져온다`() {
        val date = LocalDate.of(2023, 4, 12)

        val expected = listOf(
            LocalTime.of(9, 0), LocalTime.of(11, 0),
            LocalTime.of(13, 0), LocalTime.of(15, 0),
            LocalTime.of(17, 0), LocalTime.of(19, 0),
            LocalTime.of(21, 0), LocalTime.of(23, 0)
        )

        assertEquals(expected, TimesGenerator.getTimesByDate(date))
    }

    @Test
    fun `날짜가 주말이면 10시부터 22시까지 2시간 간격의 시간 리스트를 가져온다`() {
        val date = LocalDate.of(2023, 4, 15)

        val expected = listOf(
            LocalTime.of(10, 0), LocalTime.of(12, 0),
            LocalTime.of(14, 0), LocalTime.of(16, 0),
            LocalTime.of(18, 0), LocalTime.of(20, 0),
            LocalTime.of(22, 0)
        )

        assertEquals(expected, TimesGenerator.getTimesByDate(date))
    }
}
