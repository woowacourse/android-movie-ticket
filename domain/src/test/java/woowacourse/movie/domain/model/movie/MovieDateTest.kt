package woowacourse.movie.domain.model.movie

import org.junit.Assert.assertEquals
import org.junit.Test
import java.time.LocalDate

internal class MovieDateTest {

    @Test
    internal fun `상영일 범위 내에서 현재 날짜부터 마지막 상영일까지 목록을 반환한다`() {
        val movieDate: List<MovieDate> = MovieDate.releaseDates(
            today = LocalDate.of(2023, 4, 10),
            from = LocalDate.of(2023, 4, 1),
            to = LocalDate.of(2023, 4, 28),
        )

        val actual = movieDate.map { it.day }
        val expected = (10..28).toList()
        assertEquals(actual, expected)
    }

    @Test
    internal fun `오늘이 상영일 이전이라면 모든 상영일 목록을 반환한다`() {
        val movieDate: List<MovieDate> = MovieDate.releaseDates(
            today = LocalDate.of(2023, 3, 25),
            from = LocalDate.of(2023, 4, 1),
            to = LocalDate.of(2023, 4, 28),
        )

        val actual = movieDate.map { it.day }
        val expected = (1..28).toList()
        assertEquals(actual, expected)
    }

    @Test
    internal fun `오늘이 상영일 이후라면 빈 목록을 반환한다`() {
        val movieDate: List<MovieDate> = MovieDate.releaseDates(
            today = LocalDate.of(2023, 4, 29),
            from = LocalDate.of(2023, 4, 1),
            to = LocalDate.of(2023, 4, 28),
        )

        val actual = movieDate.map { it.day }
        val expected = emptyList<MovieDate>()
        assertEquals(actual, expected)
    }

    @Test
    internal fun `주말인지 확인한다`() {
        val movieDate = MovieDate(2023, 4, 15)
        val actual = movieDate.isWeekend()
        assertEquals(actual, true)
    }

    @Test
    internal fun `주말이 아닌지 확인한다`() {
        val movieDate = MovieDate(2023, 4, 13)
        val actual = movieDate.isWeekend()
        assertEquals(actual, false)
    }

    @Test
    internal fun `오늘인지 확인한다`() {
        val movieDate = MovieDate(LocalDate.now())
        val actual = movieDate.isToday()
        assertEquals(actual, true)
    }

    @Test
    internal fun `오늘이 아닌지 확인한다`() {
        val movieDate = MovieDate(LocalDate.now().plusDays(1))
        val actual = movieDate.isToday()
        assertEquals(actual, false)
    }
}
