package woowacourse.movie.domain.model.cinema.ticket

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll
import woowacourse.movie.domain.model.cinema.DiceCinemaPricePolicy
import woowacourse.movie.domain.model.cinema.screen.Seat
import woowacourse.movie.domain.model.cinema.screen.SeatType
import woowacourse.movie.domain.model.reservation.ReservationCount
import woowacourse.movie.domain.model.reservation.ReservationInfo
import java.time.LocalDateTime

class TicketMachineTest {
    private val policy = DiceCinemaPricePolicy()
    private val ticketMachine = TicketMachine(policy)
    private val seats =
        listOf(
            Seat(1, 1, SeatType.S_CLASS),
            Seat(1, 2, SeatType.S_CLASS),
        )
    private lateinit var fakeReservationInfo: ReservationInfo

    @BeforeEach
    fun setUp() {
        fakeReservationInfo =
            ReservationInfo(
                "해리 포터",
                LocalDateTime.of(2025, 4, 27, 20, 0),
                ReservationCount(2),
            )
        seats.forEach { fakeReservationInfo.updateSeats(it) }
    }

    @Test
    fun `publishTickets 호출 시 TicketBundle 이 올바르게 생성된다`() {
        val ticketBundle = ticketMachine.publishTickets(fakeReservationInfo)

        assertAll(
            { assertThat(ticketBundle.title).isEqualTo(fakeReservationInfo.title) },
            { assertThat(ticketBundle.size).isEqualTo(seats.size) },
            { assertThat(ticketBundle.dateTime).isEqualTo(fakeReservationInfo.reservationDateTime) },
            {
                val expectedTotalPrice = seats.sumOf { policy.calculatePrice(it.type) }
                assertThat(ticketBundle.totalPrice).isEqualTo(expectedTotalPrice)
            },
            { assertThat(ticketBundle.labels).containsExactlyElementsOf(seats) },
        )
    }
}
