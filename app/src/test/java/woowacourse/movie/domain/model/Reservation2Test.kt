package woowacourse.movie.domain.model

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows

class Reservation2Test {
    @Test
    fun `throw exception when the count of seats is not equal to count of ticket`() {
        assertThrows<IllegalArgumentException> {
            Reservation2(
                1,
                Screen.NULL,
                Ticket(1),
                Seats(
                    Seat(Position(0, 0), Grade.S),
                    Seat(Position(0, 1), Grade.A),
                ),
            )
        }
    }

    @Test
    fun `the count of seats is equal to count of ticket`() {
        assertDoesNotThrow {
            Reservation2(
                1,
                Screen.NULL,
                Ticket(2),
                Seats(
                    Seat(Position(0, 0), Grade.S),
                    Seat(Position(0, 1), Grade.A),
                ),
            )
        }
    }
}
