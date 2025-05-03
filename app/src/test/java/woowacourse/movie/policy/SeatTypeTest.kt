package woowacourse.movie.policy

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import woowacourse.movie.domain.policy.SeatType

class SeatTypeTest {
    private lateinit var seatType: SeatType
    private lateinit var seatName: String

    @Test
    fun `A 행은 B등급이며 좌석 요금은 10000원이다`() {
        // Given
        seatName = "A"
        seatType = SeatType.fromSeat(seatName)

        // Then
        seatType.price shouldBe 10000
    }

    @Test
    fun `B 행은 B등급이며 좌석 요금은 10000원이다`() {
        // Given
        seatName = "B"
        seatType = SeatType.fromSeat(seatName)

        // Then
        seatType shouldBe SeatType.B
        seatType.price shouldBe 10000
    }

    @Test
    fun `C 행은 S등급이며 좌석 요금은 15000원이다`() {
        // Given
        seatName = "C"
        seatType = SeatType.fromSeat(seatName)

        // Then
        seatType shouldBe SeatType.S
        seatType.price shouldBe 15000
    }

    @Test
    fun `D 행은 S등급이며 좌석 요금은 15000원이다`() {
        // Given
        seatName = "D"
        seatType = SeatType.fromSeat(seatName)

        // Then
        seatType shouldBe SeatType.S
        seatType.price shouldBe 15000
    }

    @Test
    fun `E 행은 A등급이며 좌석 요금은 12000원이다`() {
        // Given
        seatName = "E"
        seatType = SeatType.fromSeat(seatName)

        // Then
        seatType shouldBe SeatType.A
        seatType.price shouldBe 12000
    }
}
