package woowacourse.movie.model

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.time.LocalDate

class AvailableTimesTest {
    @Test
    fun `날짜를_주중으로_설정_시_올바른_상영시간_데이터를_보유한다`() {
        // given
        val availableTimes = AvailableTimes.of(LocalDate.of(2024, 4, 25))
        // then
        assertEquals(
            listOf(
                "09:00",
                "11:00",
                "13:00",
                "15:00",
                "17:00",
                "19:00",
                "21:00",
                "23:00",
            ),
            availableTimes.value,
        )
    }

    @Test
    fun `날짜를_주말로_설정_시_올바른_상영시간_데이터를_보유한다`() {
        // given
        val availableTimes = AvailableTimes.of(LocalDate.of(2024, 4, 27))
        // then
        assertEquals(
            listOf(
                "10:00",
                "12:00",
                "14:00",
                "16:00",
                "18:00",
                "20:00",
                "22:00",
                "00:00",
            ),
            availableTimes.value,
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
