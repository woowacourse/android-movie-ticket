package woowacourse.movie.domain.model.booking

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows
import java.time.LocalDate
import java.time.LocalTime

class BookingResultTest {
    @Test
    fun `예매한 영화 제목은 비어있을 수 없다`() {
        assertThrows<IllegalArgumentException> {
            BookingResult("", 0, LocalDate.of(2025, 4, 17), LocalTime.of(11, 0))
        }

        assertDoesNotThrow {
            BookingResult("해리포터", 0, LocalDate.of(2025, 4, 17), LocalTime.of(11, 0))
        }
    }

    @Test
    fun `예매 인원에 맞는 금액을 계산한다`() {
        val bookingResult = BookingResult("해리포터", 2, LocalDate.of(2025, 4, 17), LocalTime.of(11, 0))
        val expected = 26_000

        val actual = bookingResult.calculateAmount()

        assertEquals(expected, actual)
    }

    @Test
    fun `예매 인원이 0보다 큰 지 비교한다`() {
        val bookingResult = BookingResult("해리포터", 1, LocalDate.of(2025, 4, 17), LocalTime.of(11, 0))

        val actual = bookingResult.isHeadCountValid()
        assertTrue(actual)

        val bookingResult2 = BookingResult("해리포터", 0, LocalDate.of(2025, 4, 17), LocalTime.of(11, 0))
        val actual2 = bookingResult2.isHeadCountValid()
        assertFalse(actual2)
    }
}
