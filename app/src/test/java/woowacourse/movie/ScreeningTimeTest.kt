package woowacourse.movie

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import woowacourse.movie.domain.model.ScreeningTime
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

class ScreeningTimeTest {
    private val screeningTime: ScreeningTime = ScreeningTime()

    @Test
    fun `주말엔 10시 부터 2시간 간격으로 상영 시간을 반환한다`() {
        val result =
            screeningTime.getAvailableScreeningTimes(
                LocalDateTime.of(2025, 4, 19, 7, 20),
                LocalDate.of(2025, 5, 17),
            )

        assertEquals(
            result,
            listOf(
                LocalTime.of(9, 0),
                LocalTime.of(11, 0),
                LocalTime.of(13, 0),
                LocalTime.of(15, 0),
                LocalTime.of(17, 0),
                LocalTime.of(19, 0),
                LocalTime.of(21, 0),
                LocalTime.of(23, 0),
            ),
        )
    }

    @Test
    fun `평일엔 9시부터 2시간 간격으로 상영 시간을 반환한다`() {
        val result =
            screeningTime.getAvailableScreeningTimes(
                LocalDateTime.of(2025, 4, 9, 3, 0),
                LocalDate.of(2025, 4, 17),
            )

        assertEquals(
            result,
            listOf(
                LocalTime.of(10, 0),
                LocalTime.of(12, 0),
                LocalTime.of(14, 0),
                LocalTime.of(16, 0),
                LocalTime.of(18, 0),
                LocalTime.of(20, 0),
                LocalTime.of(22, 0),
            ),
        )
    }

    @Test
    fun `선택된 예매 날짜가 당일이면 당일 시간 이전은 제외한 예매 시간을 반환한다`() {
        val result =
            screeningTime.getAvailableScreeningTimes(
                LocalDateTime.of(2025, 4, 16, 15, 0),
                LocalDate.of(2025, 4, 16),
            )
        assertEquals(
            result,
            listOf(
                LocalTime.of(16, 0),
                LocalTime.of(18, 0),
                LocalTime.of(20, 0),
                LocalTime.of(22, 0),
            ),
        )
    }
}
