package woowacourse.movie.domain.model

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.time.LocalDate

class MovieDatesTest {
    @Test
    fun `startDate부터 endDate 사이의 모든 날짜 리스트를 생성한다`() {
        // given
        val start = MovieDate(LocalDate.of(2025, 4, 1))
        val end = MovieDate(LocalDate.of(2025, 4, 4))

        // when
        val movieDates = MovieDates(start, end)

        // then
        val expected =
            listOf(
                MovieDate(LocalDate.of(2025, 4, 1)),
                MovieDate(LocalDate.of(2025, 4, 2)),
                MovieDate(LocalDate.of(2025, 4, 3)),
                MovieDate(LocalDate.of(2025, 4, 4)),
            )
        assertEquals(expected, movieDates.value)
    }

    @Test
    fun `startDate와 endDate가 같으면 하나의 날짜만 포함한다`() {
        // given
        val date = MovieDate(LocalDate.of(2025, 4, 24))

        // when
        val movieDates = MovieDates(date, date)

        // then
        val expected = listOf(date)
        assertEquals(expected, movieDates.value)
    }
}
