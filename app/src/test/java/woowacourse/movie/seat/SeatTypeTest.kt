package woowacourse.movie.seat

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import woowacourse.movie.domain.seat.Seat
import woowacourse.movie.domain.seat.SeatType

class SeatTypeTest {
    private lateinit var seatType: SeatType
    private lateinit var seat: Seat

    @Test
    fun `A 행은 B등급이며 좌석 요금은 10000원이다`() {
        // Given
        seat = Seat(0, 0)
        seatType = SeatType.fromSeat(seat)

        // Then
        seatType.price shouldBe 10000
    }

    @Test
    fun `B 행은 B등급이며 좌석 요금은 10000원이다`() {
        // Given
        seat = Seat(1, 0)
        seatType = SeatType.fromSeat(seat)

        // Then
        seatType shouldBe SeatType.B
        seatType.price shouldBe 10000
    }

    @Test
    fun `C 행은 S등급이며 좌석 요금은 15000원이다`() {
        // Given
        seat = Seat(2, 0)
        seatType = SeatType.fromSeat(seat)

        // Then
        seatType shouldBe SeatType.S
        seatType.price shouldBe 15000
    }

    @Test
    fun `D 행은 S등급이며 좌석 요금은 15000원이다`() {
        // Given
        seat = Seat(3, 0)
        seatType = SeatType.fromSeat(seat)

        // Then
        seatType shouldBe SeatType.S
        seatType.price shouldBe 15000
    }

    @Test
    fun `E 행은 A등급이며 좌석 요금은 12000원이다`() {
        // Given
        seat = Seat(4, 0)
        seatType = SeatType.fromSeat(seat)

        // Then
        seatType shouldBe SeatType.A
        seatType.price shouldBe 12000
    }
}
