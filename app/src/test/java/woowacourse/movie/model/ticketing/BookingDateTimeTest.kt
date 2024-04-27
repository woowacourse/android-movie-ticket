package woowacourse.movie.model.ticketing

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll
import java.time.LocalDate
import java.time.LocalTime

class BookingDateTimeTest {
    @Test
    fun `문자열_형태의_날짜와_시간_제공_시_올바른_형태의_LocalDate_및_LocalTime_데이터를_보유한다`() {
        // given
        val bookingDateTime = BookingDateTime.of("2024-01-01", "11:00")
        // then
        assertAll(
            { assertEquals(LocalDate.of(2024, 1, 1), bookingDateTime.date) },
            { assertEquals(LocalTime.of(11, 0), bookingDateTime.time) },
        )
    }
}
