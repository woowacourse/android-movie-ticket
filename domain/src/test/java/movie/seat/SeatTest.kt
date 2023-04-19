package movie.seat

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class SeatTest {
    @Test
    fun `A1 좌석의 가격은 10_000원이다`() {
        // given
        val seat = Seat(SeatRow.A, SeatColumn.ONE)
        val actual = 10_000

        // when
        val expected = seat.getSeatPrice()

        // then
        assertThat(expected).isEqualTo(actual)
    }

    @Test
    fun `B1 좌석의 가격은 10_000원이다`() {
        // given
        val seat = Seat(SeatRow.B, SeatColumn.ONE)
        val actual = 10_000

        // when
        val expected = seat.getSeatPrice()

        // then
        assertThat(expected).isEqualTo(actual)
    }

    @Test
    fun `C1 좌석의 가격은 15_000원이다`() {
        // given
        val seat = Seat(SeatRow.C, SeatColumn.ONE)
        val actual = 15_000

        // when
        val expected = seat.getSeatPrice()

        // then
        assertThat(expected).isEqualTo(actual)
    }

    @Test
    fun `D1 좌석의 가격은 15_000원이다`() {
        // given
        val seat = Seat(SeatRow.D, SeatColumn.ONE)
        val actual = 15_000

        // when
        val expected = seat.getSeatPrice()

        // then
        assertThat(expected).isEqualTo(actual)
    }

    @Test
    fun `E1 좌석의 가격은 12_000원이다`() {
        // given
        val seat = Seat(SeatRow.E, SeatColumn.ONE)
        val actual = 12_000

        // when
        val expected = seat.getSeatPrice()

        // then
        assertThat(expected).isEqualTo(actual)
    }
}
