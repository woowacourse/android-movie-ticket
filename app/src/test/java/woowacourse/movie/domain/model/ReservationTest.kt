package woowacourse.movie.domain.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import woowacourse.movie.APRIL_THIRTIETH
import woowacourse.movie.MAY_FIRST
import java.time.LocalDateTime
import java.time.LocalTime

class ReservationTest {
    private lateinit var reservation: Reservation

    @BeforeEach
    fun setUp() {
        reservation =
            Reservation(
                "해리포터",
                LocalDateTime.of(
                    APRIL_THIRTIETH,
                    LocalTime.of(12, 0),
                ),
                Tickets(listOf(Ticket(Price(1_3000)))),
            )
    }

    @Test
    fun `총 가격을 계산한다`() {
        // given & when
        val actual = reservation.totalPrice()
        val expected = 1_3000

        // then
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `예매할 날짜를 변경한다`() {
        // given
        val expected =
            LocalDateTime.of(
                MAY_FIRST,
                LocalTime.of(12, 0),
            )

        // when
        val actual = reservation.updateReservedTime(expected).reservedTime

        // then
        assertThat(actual).isEqualTo(expected)
    }
}
