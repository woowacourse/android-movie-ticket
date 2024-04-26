package woowacourse.movie.model.screening

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.time.LocalDate
import java.time.LocalTime

class AvailableTimesTest {
    @Test
    fun `날짜를_주중으로_설정_시_올바른_상영시간_데이터를_보유한다`() {
        // given
        val availableTimes = AvailableTimes.of(LocalDate.of(2024, 4, 25))
        // then
        assertEquals(
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
            availableTimes.localTimes,
        )
    }

    @Test
    fun `날짜를_주말로_설정_시_올바른_상영시간_데이터를_보유한다`() {
        // given
        val availableTimes = AvailableTimes.of(LocalDate.of(2024, 4, 27))
        // then
        assertEquals(
            listOf(
                LocalTime.of(10, 0),
                LocalTime.of(12, 0),
                LocalTime.of(14, 0),
                LocalTime.of(16, 0),
                LocalTime.of(18, 0),
                LocalTime.of(20, 0),
                LocalTime.of(22, 0),
                LocalTime.of(0, 0),
            ),
            availableTimes.localTimes,
        )
    }

    @Test
    fun `하루_최대_상영_횟수를_초과하는_값을_상영_횟수로_설정하면_예외를_발생시킨다`() {
        assertThrows<IllegalArgumentException>("하루 상영 횟수는 1에서 24회 사이입니다.") {
            AvailableTimes.of(LocalDate.of(2024, 1, 1), 25, 1)
        }
    }

    @Test
    fun `하루_최소_상영_횟수_미만의_값을_상영_횟수로_설정하면_예외를_발생시킨다`() {
        assertThrows<IllegalArgumentException>("하루 상영 횟수는 1에서 24회 사이입니다.") {
            AvailableTimes.of(LocalDate.of(2024, 1, 1), 0, 1)
        }
    }

    @Test
    fun `최대_상영_간격을_초과하는_값을_상영_간격으로_설정하면_예외를_발생시킨다`() {
        assertThrows<IllegalArgumentException>("하루 상영 간격은 1에서 24시간 사이입니다.") {
            AvailableTimes.of(LocalDate.of(2024, 1, 1), 1, 25)
        }
    }

    @Test
    fun `최소_상영_간격_미만의_값을_상영_간격으로_설정하면_예외를_발생시킨다`() {
        assertThrows<IllegalArgumentException>("하루 상영 간격은 1에서 24시간 사이입니다.") {
            AvailableTimes.of(LocalDate.of(2024, 1, 1), 1, 0)
        }
    }
}
