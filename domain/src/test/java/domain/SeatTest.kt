package domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

class SeatTest {

    @Test
    fun `좌석이 1행 2열일 때, 조조_야간 할인을 적용한 티켓의 가격은 8000원이다`() {
        val position = Position(1, 2)
        val seat = Seat(position, TicketPrice.of(position))
        val date = LocalDate.of(2023, 3, 1)
        val time = LocalTime.of(9, 0)

        assertThat(seat.applyPolicyPrice(LocalDateTime.of(date, time))).isEqualTo(8000)
    }

    @Test
    fun `좌석이 3행 2열일 때, 조조_야간 할인을 적용한 티켓의 가격은 13000원이다`() {
        val position = Position(3, 2)
        val seat = Seat(position, TicketPrice.of(position))
        val date = LocalDate.of(2023, 3, 1)
        val time = LocalTime.of(9, 0)

        assertThat(seat.applyPolicyPrice(LocalDateTime.of(date, time))).isEqualTo(13000)
    }

    @Test
    fun `좌석이 5행 2열일 때, 조조_야간 할인을 적용한 티켓의 가격은 10000원이다`() {
        val position = Position(5, 2)
        val seat = Seat(position, TicketPrice.of(position))
        val date = LocalDate.of(2023, 3, 1)
        val time = LocalTime.of(9, 0)

        assertThat(seat.applyPolicyPrice(LocalDateTime.of(date, time))).isEqualTo(10000)
    }

    @Test
    fun `좌석이 1행 2열일 때, 무비데이 할인을 적용한 티켓의 가격은 9000원이다`() {
        val position = Position(1, 2)
        val seat = Seat(position, TicketPrice.of(position))
        val date = LocalDate.of(2023, 3, 10)
        val time = LocalTime.of(13, 0)

        assertThat(seat.applyPolicyPrice(LocalDateTime.of(date, time))).isEqualTo(9000)
    }

    @Test
    fun `좌석이 3행 2열일 때, 무비데이 할인을 적용한 티켓의 가격은 13500원이다`() {
        val position = Position(3, 2)
        val seat = Seat(position, TicketPrice.of(position))
        val date = LocalDate.of(2023, 3, 10)
        val time = LocalTime.of(13, 0)

        assertThat(seat.applyPolicyPrice(LocalDateTime.of(date, time))).isEqualTo(13500)
    }

    @Test
    fun `좌석이 5행 2열일 때, 무비데이 할인을 적용한 티켓의 가격은 10800원이다`() {
        val position = Position(5, 2)
        val seat = Seat(position, TicketPrice.of(position))
        val date = LocalDate.of(2023, 3, 10)
        val time = LocalTime.of(13, 0)

        assertThat(seat.applyPolicyPrice(LocalDateTime.of(date, time))).isEqualTo(10800)
    }

    @Test
    fun `좌석이 1행 2열일 때, 무비데이 할인, 조조_야간 할인을 적용한 티켓의 가격은 7000원이다`() {
        val position = Position(1, 2)
        val seat = Seat(position, TicketPrice.of(position))
        val date = LocalDate.of(2023, 3, 10)
        val time = LocalTime.of(9, 0)

        assertThat(seat.applyPolicyPrice(LocalDateTime.of(date, time))).isEqualTo(7000)
    }

    @Test
    fun `좌석이 3행 2열일 때, 무비데이 할인, 조조_야간 할인을 적용한 티켓의 가격은 11500원이다`() {
        val position = Position(3, 2)
        val seat = Seat(position, TicketPrice.of(position))
        val date = LocalDate.of(2023, 3, 10)
        val time = LocalTime.of(9, 0)

        assertThat(seat.applyPolicyPrice(LocalDateTime.of(date, time))).isEqualTo(11500)
    }

    @Test
    fun `좌석이 5행 2열일 때, 무비데이 할인, 조조_야간 할인을 적용한 티켓의 가격은 8800원이다`() {
        val position = Position(5, 2)
        val seat = Seat(position, TicketPrice.of(position))
        val date = LocalDate.of(2023, 3, 10)
        val time = LocalTime.of(9, 0)

        assertThat(seat.applyPolicyPrice(LocalDateTime.of(date, time))).isEqualTo(8800)
    }

}
