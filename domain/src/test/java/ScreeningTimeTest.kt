package woowacourse.movie.domain

import org.junit.Test
import java.time.LocalDate
import java.time.LocalTime

class ScreeningTimeTest {

    @Test
    fun `영화의 상영일이 평일이라면 상영 시간은 오전 10시부터 자정까지 2시간 간격으로 있다`() {

        val screeningDate = LocalDate.of(2023, 3, 1) // 수요일
        val screeningTime = ScreeningTime(screeningDate)

        val actual = screeningTime.getAllScreeningTimes()

        val expected = listOf(
            LocalTime.of(10, 0),
            LocalTime.of(12, 0),
            LocalTime.of(14, 0),
            LocalTime.of(16, 0),
            LocalTime.of(18, 0),
            LocalTime.of(20, 0),
            LocalTime.of(22, 0),
            LocalTime.of(0, 0)
        )
        assert(expected == actual)
    }

    @Test
    fun `영화의 상영일이 주말이라면 상영 시간은 오전 9시부터 자정까지 2시간 간격으로 있다`() {

        val screeningDate = LocalDate.of(2023, 3, 4) // 토요일
        val screeningTime = ScreeningTime(screeningDate)

        val actual = screeningTime.getAllScreeningTimes()

        val expected = listOf(
            LocalTime.of(9, 0),
            LocalTime.of(11, 0),
            LocalTime.of(13, 0),
            LocalTime.of(15, 0),
            LocalTime.of(17, 0),
            LocalTime.of(19, 0),
            LocalTime.of(21, 0),
            LocalTime.of(23, 0)
        )
        assert(expected == actual)
    }
}
