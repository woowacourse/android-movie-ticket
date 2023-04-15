package domain.movie

import org.junit.Assert.assertEquals
import org.junit.Test
import java.time.LocalDate

internal class ScreeningPeriodTest {

    @Test
    fun `상영_시작일은_상영_종료일_이전이어야_한다`() {
        ScreeningPeriod(
            startDate = ScreeningDate(LocalDate.of(2023, 4, 12)),
            endDate = ScreeningDate(LocalDate.of(2023, 4, 12)),
        )
    }

    @Test(expected = IllegalArgumentException::class)
    fun `상영_시작일_보다_상영_종료일이_이전일_경우_에러를_발생시킨다`() {
        ScreeningPeriod(
            startDate = ScreeningDate(LocalDate.of(2023, 4, 12)),
            endDate = ScreeningDate(LocalDate.of(2023, 4, 11)),
        )
    }

    @Test
    fun `상영_시작일과_상영_종료일_사이의_모든_상영일을_반환`() {
        val screeningDates: List<ScreeningDate> = ScreeningPeriod(
            startDate = ScreeningDate(LocalDate.of(2023, 4, 10)),
            endDate = ScreeningDate(LocalDate.of(2023, 4, 12)),
        ).getScreeningDates()

        val expectedDates: List<ScreeningDate> = listOf(
            ScreeningDate(LocalDate.of(2023, 4, 10)),
            ScreeningDate(LocalDate.of(2023, 4, 11)),
            ScreeningDate(LocalDate.of(2023, 4, 12)),
        )

        assertEquals(expectedDates, screeningDates)
    }
}
