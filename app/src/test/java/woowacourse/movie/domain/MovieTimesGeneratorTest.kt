package woowacourse.movie.domain

import org.junit.Assert.assertEquals
import org.junit.Test
import java.time.LocalDate

class MovieTimesGeneratorTest {
    @Test
    fun `날짜가 평일이면 9시부터 23시까지 2시간 간격의 시간 리스트를 가져온다`() {
        val date = LocalDate.of(2023, 4, 12)

        val expected = listOf(
            MovieTime(9), MovieTime(11),
            MovieTime(13), MovieTime(15),
            MovieTime(17), MovieTime(19),
            MovieTime(21), MovieTime(23)
        )

        assertEquals(expected, MovieTimesGenerator.getTimesByDate(date))
    }

    @Test
    fun `날짜가 주말이면 10시부터 24시까지 2시간 간격의 시간 리스트를 가져온다`() {
        val date = LocalDate.of(2023, 4, 15)

        val expected = listOf(
            MovieTime(10), MovieTime(12),
            MovieTime(14), MovieTime(16),
            MovieTime(18), MovieTime(20),
            MovieTime(22), MovieTime(24)
        )

        assertEquals(expected, MovieTimesGenerator.getTimesByDate(date))
    }
}
