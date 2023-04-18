package woowacourse.movie.domain.datetime

import org.junit.Assert
import org.junit.Test
import woowacourse.movie.model.ScreeningPeriodState
import java.time.LocalDate
import java.time.LocalTime

class ScreeningPeriodStateTest {

    @Test(expected = IllegalArgumentException::class)
    fun `영화 상영시작 일자가 상영종료 일자보다 크다면 객체를 생성할수 없다`() {
        ScreeningPeriodState(
            LocalDate.parse("2023-02-01"),
            LocalDate.parse("2023-01-01")
        )
    }

    @Test
    fun `주말이라면 주말 시간표를 반환한다`() {
        val screeningPeriod = ScreeningPeriodState(
            LocalDate.parse("2023-02-01"),
            LocalDate.parse("2023-03-01")
        )
        Assert.assertEquals(
            screeningPeriod.getScreeningTime(LocalDate.parse("2023-02-11")),
            convertStringListToLocalTimeList(
                listOf(
                    "10:00",
                    "12:00",
                    "14:00",
                    "16:00",
                    "18:00",
                    "20:00",
                    "22:00",
                    "00:00"
                )
            )
        )
    }

    @Test
    fun `주증이라면 주증 시간표를 반환한다`() {
        val screeningPeriod = ScreeningPeriodState(
            LocalDate.parse("2023-02-01"),
            LocalDate.parse("2023-03-01")
        )
        Assert.assertEquals(
            screeningPeriod.getScreeningTime(LocalDate.parse("2023-02-10")),
            convertStringListToLocalTimeList(
                listOf(
                    "09:00",
                    "11:00",
                    "13:00",
                    "15:00",
                    "17:00",
                    "19:00",
                    "21:00",
                    "23:00"
                )
            )
        )
    }

    @Test
    fun `2023-03-03 ~ 2023-03-05 는 03,04,05 일의 리스트를 반환한다`() {
        val screeningPeriod = ScreeningPeriodState(
            LocalDate.parse("2023-03-03"),
            LocalDate.parse("2023-03-05")
        )
        Assert.assertEquals(
            screeningPeriod.getScreeningDates(),
            listOf(
                LocalDate.parse("2023-03-03"),
                LocalDate.parse("2023-03-04"),
                LocalDate.parse("2023-03-05")
            )
        )
    }

    companion object {
        private fun convertStringListToLocalTimeList(times: List<String>): List<LocalTime> =
            times.map { LocalTime.parse(it) }
    }
}
