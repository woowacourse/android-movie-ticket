package woowacourse.movie.domain

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import woowacourse.movie.domain.model.booking.ScreeningTime
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

class ScreeningTimeTest {
    @Test
    fun `주말엔 10시 부터 2시간 간격으로 상영 시간을 반환한다`() {
        // when
        val screeningTime =
            ScreeningTime(
                LocalDateTime.of(2025, 4, 24, 15, 20),
                LocalDate.of(2025, 4, 26),
            )

        // given
        val result =
            screeningTime.getAvailableScreeningTimes()

        // then
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
        // when
        val screeningTime =
            ScreeningTime(
                LocalDateTime.of(2025, 4, 24, 15, 20),
                LocalDate.of(2025, 4, 25),
            )

        // given
        val result = screeningTime.getAvailableScreeningTimes()

        // result
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
        // when
        val screeningTime =
            ScreeningTime(
                LocalDateTime.of(2025, 4, 24, 15, 20),
                LocalDate.of(2025, 4, 24),
            )

        // given
        val result = screeningTime.getAvailableScreeningTimes()

        // then
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
