package woowacourse.movie.domain.model.reservation

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import woowacourse.movie.APRIL_THIRTIETH
import woowacourse.movie.MAY_FIRST
import woowacourse.movie.domain.model.seat.Column
import woowacourse.movie.domain.model.seat.Row
import woowacourse.movie.domain.model.seat.Seat
import woowacourse.movie.domain.model.seat.SeatRate
import woowacourse.movie.domain.model.ticket.Ticket
import woowacourse.movie.domain.model.ticket.Tickets
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
                Tickets(
                    listOf(
                        Ticket(Seat(Row(0), Column(0), SeatRate.S)),
                    ),
                ),
            )
    }

    @Test
    fun `총 가격을 계산한다`() {
        // given & when
        val actual = reservation.totalPrice()
        val expected = 1_5000

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
