package woowacourse.movie.domain

import org.junit.Assert.assertEquals
import org.junit.Test
import java.time.LocalDate

class TimesGeneratorTest {
    @Test
    fun `날짜가 평일이면 9시부터 23시까지 2시간 간격의 시간 리스트를 가져온다`() {
        val date = LocalDate.of(2023, 4, 12)

        val expected = listOf(
            Time(9), Time(11),
            Time(13), Time(15),
            Time(17), Time(19),
            Time(21), Time(23)
        )

        assertEquals(expected, TimesGenerator.getTimesByDate(date))
    }

    @Test
    fun `날짜가 주말이면 10시부터 24시까지 2시간 간격의 시간 리스트를 가져온다`() {
        val date = LocalDate.of(2023, 4, 15)

        val expected = listOf(
            Time(10), Time(12),
            Time(14), Time(16),
            Time(18), Time(20),
            Time(22), Time(24)
        )

        assertEquals(expected, TimesGenerator.getTimesByDate(date))
    }
}
