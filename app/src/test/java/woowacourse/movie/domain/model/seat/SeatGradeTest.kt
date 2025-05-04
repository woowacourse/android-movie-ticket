package woowacourse.movie.domain.model.seat

import io.kotest.assertions.assertSoftly
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class SeatGradeTest {
    @Test
    fun `1행, 2행의 좌석은 B등급이다`() {
        // Given
        val seat1 = 0
        val seat2 = 1

        // When
        val grade1 = SeatGrade.from(seat1)
        val grade2 = SeatGrade.from(seat2)

        // Then
        assertSoftly {
            grade1 shouldBe SeatGrade.B
            grade2 shouldBe SeatGrade.B
        }
    }

    @Test
    fun `3행, 4행의 좌석은 S등급이다`() {
        // Given
        val seat1 = 2
        val seat2 = 3

        // When
        val grade1 = SeatGrade.from(seat1)
        val grade2 = SeatGrade.from(seat2)

        // Then
        assertSoftly {
            grade1 shouldBe SeatGrade.S
            grade2 shouldBe SeatGrade.S
        }
    }

    @Test
    fun `5행의 좌석은 A등급이다`() {
        // Given
        val seat = 4

        // When
        val grade = SeatGrade.from(seat)

        // Then
        grade shouldBe SeatGrade.A
    }

    @Test
    fun `B등급 좌석의 가격은 10000원이다`() {
        // When
        val seatB = SeatGrade.B

        // Then
        seatB.price shouldBe 10000
    }

    @Test
    fun `A등급 좌석의 가격은 12000원이다`() {
        // When
        val seatA = SeatGrade.A

        // Then
        seatA.price shouldBe 12000
    }

    @Test
    fun `S등급 좌석의 가격은 15000원이다`() {
        // When
        val seatS = SeatGrade.S

        // Then
        seatS.price shouldBe 15000
    }
}
