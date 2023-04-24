package domain.movie

import org.junit.Assert
import org.junit.Test
import java.time.LocalDate
import java.time.LocalTime

class ScreeningDateTest {

    @Test
    fun `상영일이_평일이라면_10시부터_24시까지_두시간_간격의_상영시간들을_반환한다`() {
        val screeningTimes = ScreeningDate(LocalDate.of(2023, 4, 10)).screeningTimes

        val expectedTimes: List<LocalTime> = listOf(
            LocalTime.of(10, 0),
            LocalTime.of(12, 0),
            LocalTime.of(14, 0),
            LocalTime.of(16, 0),
            LocalTime.of(18, 0),
            LocalTime.of(20, 0),
            LocalTime.of(22, 0),
        )

        Assert.assertEquals(screeningTimes, expectedTimes)
    }

    @Test
    fun `상영일이_주말이라면_9시부터_24시까지_두시간_간격의_상영시간들을_반환한다`() {
        val screeningTimes = ScreeningDate(LocalDate.of(2023, 4, 15)).screeningTimes

        val expectedTimes: List<LocalTime> = listOf(
            LocalTime.of(9, 0),
            LocalTime.of(11, 0),
            LocalTime.of(13, 0),
            LocalTime.of(15, 0),
            LocalTime.of(17, 0),
            LocalTime.of(19, 0),
            LocalTime.of(21, 0),
            LocalTime.of(23, 0)
        )

        Assert.assertEquals(screeningTimes, expectedTimes)
    }
}
