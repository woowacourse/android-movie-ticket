package woowacourse.movie.model.ticketing

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import woowacourse.movie.model.theater.SeatClass
import java.time.LocalDate
import java.time.LocalTime

class Tickets2Test {
    @Test
    fun `주어진_티켓_수에_맞는_총_금액을_계산하여_반환한다`() {
        val tickets =
            Tickets2(
                listOf(
                    Ticket(
                        0L,
                        BookingDateTime(
                            LocalDate.of(2024, 1, 1),
                            LocalTime.of(9, 0),
                        ),
                        BookingSeat(1, 1, SeatClass.A),
                    ),
                    Ticket(
                        0L,
                        BookingDateTime(
                            LocalDate.of(2024, 1, 1),
                            LocalTime.of(9, 0),
                        ),
                        BookingSeat(1, 2, SeatClass.S),
                    ),
                    Ticket(
                        0L,
                        BookingDateTime(
                            LocalDate.of(2024, 1, 1),
                            LocalTime.of(9, 0),
                        ),
                        BookingSeat(1, 3, SeatClass.B),
                    ),
                ),
            )
        Assertions.assertThat(tickets.totalPrice).isEqualTo(37000)
    }

    @Test
    fun `중복된_좌석이_입력되었을_때_예외를_발생시킨다`() {
        assertThrows<IllegalArgumentException>("예약 좌석은 중복될 수 없습니다.") {
            Tickets2(
                listOf(
                    Ticket(
                        0L,
                        BookingDateTime(
                            LocalDate.of(2024, 1, 1),
                            LocalTime.of(9, 0),
                        ),
                        BookingSeat(1, 1, SeatClass.A),
                    ),
                    Ticket(
                        0L,
                        BookingDateTime(
                            LocalDate.of(2024, 1, 1),
                            LocalTime.of(9, 0),
                        ),
                        BookingSeat(1, 1, SeatClass.A),
                    ),
                    Ticket(
                        0L,
                        BookingDateTime(
                            LocalDate.of(2024, 1, 1),
                            LocalTime.of(9, 0),
                        ),
                        BookingSeat(1, 3, SeatClass.B),
                    ),
                ),
            )
        }
    }
}
