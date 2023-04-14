package domain.movie

import org.junit.Assert.assertEquals
import org.junit.Test
import java.time.LocalDate
import java.time.LocalTime

internal class ScreeningPeriodTest {

    @Test
    fun `상영_시작일은_상영_종료일_이전이어야_한다`() {
        ScreeningPeriod(
            startDate = LocalDate.of(2023, 4, 12),
            endDate = LocalDate.of(2023, 4, 12),
        )
    }

    @Test(expected = IllegalArgumentException::class)
    fun `상영_시작일_보다_상영_종료일이_이전일_경우_에러를_발생시킨다`() {
        ScreeningPeriod(
            startDate = LocalDate.of(2023, 4, 12),
            endDate = LocalDate.of(2023, 4, 11),
        )
    }

    @Test
    fun `상영_시작일과_상영_종료일_사이의_모든_상영일을_반환`() {
        val screeningDates: List<LocalDate> = ScreeningPeriod(
            startDate = LocalDate.of(2023, 4, 10),
            endDate = LocalDate.of(2023, 4, 12),
        ).getScreeningDates()
        val expectedDates: List<LocalDate> = listOf(
            LocalDate.of(2023, 4, 10),
            LocalDate.of(2023, 4, 11),
            LocalDate.of(2023, 4, 12)
        )

        assertEquals(expectedDates, screeningDates)
    }

    @Test
    fun `상영일이_평일이라면_10시부터_24시까지_두시간_간격의_상영시간들을_반환한다`() {
        val screeningTimes: List<LocalTime> = ScreeningPeriod(
            startDate = LocalDate.of(2023, 4, 10),
            endDate = LocalDate.of(2023, 4, 15),
        ).getScreeningTimes(LocalDate.of(2023, 4, 14))
        val expectedTimes: List<LocalTime> = listOf(
            LocalTime.of(10, 0),
            LocalTime.of(12, 0),
            LocalTime.of(14, 0),
            LocalTime.of(16, 0),
            LocalTime.of(18, 0),
            LocalTime.of(20, 0),
            LocalTime.of(22, 0),
        )

        assertEquals(screeningTimes, expectedTimes)
    }

    @Test
    fun `상영일이_주말이라면_9시부터_24시까지_두시간_간격의_상영시간들을_반환한다`() {
        val screeningTimes: List<LocalTime> = ScreeningPeriod(
            startDate = LocalDate.of(2023, 4, 10),
            endDate = LocalDate.of(2023, 4, 15),
        ).getScreeningTimes(LocalDate.of(2023, 4, 15))
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

        assertEquals(screeningTimes, expectedTimes)
    }
}
