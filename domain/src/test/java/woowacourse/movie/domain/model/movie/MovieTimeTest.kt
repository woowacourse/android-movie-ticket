package woowacourse.movie.domain.model.movie

import junitparams.JUnitParamsRunner
import junitparams.Parameters
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import java.time.LocalTime

@RunWith(JUnitParamsRunner::class)
internal class MovieTimeTest {

    @Test
    internal fun `주말 영화 상영시간은 오전 9시부터 자정까지 두 시간 간격이다`() {
        val movieTimes = MovieTime.runningTimes(false, isToday = false)
        val actual = movieTimes.map { it.hour * 60 + it.min }
        val expected = (9 until 24 step 2).map { it * 60 }
        assertEquals(actual, expected)
    }

    @Test
    internal fun `평일 영화 상영시간은 오전 10시부터 자정까지 두 시간 간격이다`() {
        val movieTimes = MovieTime.runningTimes(true, isToday = false)
        val actual = movieTimes.map { it.hour * 60 + it.min }
        val expected = (10 until 24 step 2).map { it * 60 }
        assertEquals(actual, expected)
    }

    @Test
    internal fun `평일 현재 시간이 11시면 영화 상영시간은 12시부터 두 시간 간격이다`() {
        val movieTimes = MovieTime.runningTimes(true, true, LocalTime.of(11, 0))
        val actual = movieTimes.map { it.hour * 60 + it.min }
        val expected = (12 until 24 step 2).map { it * 60 }
        assertEquals(actual, expected)
    }

    @Test
    internal fun `주말 현재 시간이 10시면 영화 상영시간은 11시부터 두 시간 간격이다`() {
        val movieTimes = MovieTime.runningTimes(false, true, LocalTime.of(10, 0))
        val actual = movieTimes.map { it.hour * 60 + it.min }
        val expected = (11 until 24 step 2).map { it * 60 }
        assertEquals(actual, expected)
    }

    @Test
    @Parameters(
        "10, 0",
        "20, 0",
    )
    internal fun `11시 이전이고, 20시 이후이면 할인 시간이다`(hour: Int, min: Int) {
        val movieTime = MovieTime(hour, min)
        val actual = movieTime.isDiscountable()
        assertEquals(actual, true)
    }

    @Test
    @Parameters(
        "11, 1",
        "19, 59",
    )
    internal fun `11시 이후이고, 20시 이전이면 할인 시간이 아니다`(hour: Int, min: Int) {
        val movieTime = MovieTime(hour, min)
        val actual = movieTime.isDiscountable()
        assertEquals(actual, false)
    }

    @Test
    internal fun `더 오래된 시간을 반환한다`() {
        val prevMovieTime = MovieTime(10, 0)
        val nextMovieTime = MovieTime(11, 0)
        val actual = nextMovieTime > prevMovieTime
        assertEquals(actual, true)
    }
}
