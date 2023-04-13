package woowacourse.movie.domain

import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test
import java.time.LocalDate

class MovieTimeTest() {
    @Test
    fun `10일은 무비데이 이다`() {
        assertTrue(MovieTime(LocalDate.of(2023, 4, 10), Time(15)).isMovieDay())
    }

    @Test
    fun `20일은 무비데이 이다`() {
        assertTrue(MovieTime(LocalDate.of(2023, 4, 20), Time(15)).isMovieDay())
    }

    @Test
    fun `30일은 무비데이 이다`() {
        assertTrue(MovieTime(LocalDate.of(2023, 4, 30), Time(15)).isMovieDay())
    }

    @Test
    fun `14일은 무비데이가 아니다`() {
        assertFalse(MovieTime(LocalDate.of(2023, 4, 14), Time(15)).isMovieDay())
    }
}
