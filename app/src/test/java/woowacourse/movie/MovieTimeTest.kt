package woowacourse.movie

import org.junit.Assert.assertEquals
import org.junit.Test
import woowacourse.movie.domain.model.movie.MovieTime
import java.time.LocalTime

class MovieTimeTest {
    @Test
    fun `주말 영화 상영시간은 오전 9시부터 자정까지 두 시간 간격이다`() {
        val movieTimes = MovieTime.runningTimes(false, isToday = false)
        val actual = movieTimes.map { it.hour * 60 + it.min }
        val expected = (9 until 24 step 2).map { it * 60 }
        assertEquals(actual, expected)
    }

    @Test
    fun `평일 영화 상영시간은 오전 10시부터 자정까지 두 시간 간격이다`() {
        val movieTimes = MovieTime.runningTimes(true, isToday = false)
        val actual = movieTimes.map { it.hour * 60 + it.min }
        val expected = (10 until 24 step 2).map { it * 60 }
        assertEquals(actual, expected)
    }

    @Test
    fun `평일 현재 시간이 11시면 영화 상영시간은 12시부터 두 시간 간격이다`() {
        val movieTimes = MovieTime.runningTimes(true, true, LocalTime.of(11, 0))
        val actual = movieTimes.map { it.hour * 60 + it.min }
        val expected = (12 until 24 step 2).map { it * 60 }
        assertEquals(actual, expected)
    }

    @Test
    fun `주말 현재 시간이 10시면 영화 상영시간은 11시부터 두 시간 간격이다`() {
        val movieTimes = MovieTime.runningTimes(false, true, LocalTime.of(10, 0))
        val actual = movieTimes.map { it.hour * 60 + it.min }
        val expected = (11 until 24 step 2).map { it * 60 }
        assertEquals(actual, expected)
    }
}
