package woowacourse.movie.domain.datetime

import org.junit.Assert
import org.junit.Test
import woowacourse.movie.model.ScreeningDateTimeState
import woowacourse.movie.model.ScreeningPeriodState
import java.time.LocalDate
import java.time.LocalDateTime

class ScreeningDateTimeStateTest {
    fun getWorkingScreeningDateTime(day: Int = 3, time: Int = 11): ScreeningDateTimeState {
        val workingScreeningPeriod = ScreeningPeriodState(
            LocalDate.parse("2023-03-01"),
            LocalDate.parse("2023-04-01")
        )
        return ScreeningDateTimeState(
            LocalDateTime.of(2023, 3, day, time, 11, 11),
            workingScreeningPeriod
        )
    }

    @Test(expected = IllegalArgumentException::class)
    fun `영화 상영시간은 영화 상영 기간안에 존재해야한다`() {
        val screeningPeriod = ScreeningPeriodState(
            LocalDate.parse("2023-02-01"),
            LocalDate.parse("2023-03-01")
        )
        ScreeningDateTimeState(LocalDateTime.of(2024, 1, 1, 11, 11, 11), screeningPeriod)
    }

    @Test
    fun `10일이라면 무비데이이므로 true를 반환한다`() {
        val workingScreeningDateTime = getWorkingScreeningDateTime(day = 10)
        Assert.assertTrue(workingScreeningDateTime.checkMovieDay())
    }

    @Test
    fun `20일이라면 무비데이이므로 true를 반환한다`() {
        val workingScreeningDateTime = getWorkingScreeningDateTime(day = 20)
        Assert.assertTrue(workingScreeningDateTime.checkMovieDay())
    }

    @Test
    fun `30일이라면 무비데이이므로 true를 반환한다`() {
        val workingScreeningDateTime = getWorkingScreeningDateTime(day = 30)
        Assert.assertTrue(workingScreeningDateTime.checkMovieDay())
    }

    @Test
    fun `11시 이전이라면 조조이다`() {
        val workingScreeningDateTime = getWorkingScreeningDateTime(time = 10)
        Assert.assertTrue(workingScreeningDateTime.checkEarlyMorningLateNight())
    }

    @Test
    fun `20시 이후라면 심야이다`() {
        val workingScreeningDateTime = getWorkingScreeningDateTime(time = 21)
        Assert.assertTrue(workingScreeningDateTime.checkEarlyMorningLateNight())
    }
}
