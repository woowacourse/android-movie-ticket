package woowacourse.movie.domain.model

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import woowacourse.movie.presentation.seat.model.SeatSelectType

class MovieSeatsTest {

    @Test
    fun `getSeatPrice 메서드가 올바른 가격을 반환하는지 확인한다`() {
        // Given
        val seatA = MovieSeat("A1", SeatType.A)
        val seatB = MovieSeat("B2", SeatType.B)
        val seats = arrayListOf(seatA, seatB)
        val movieSeats = MovieSeats(ticketCount = 5)
        movieSeats.userSeats.addAll(seats)

        // When
        val totalPrice = movieSeats.getSeatPrice()

        // Then
        assertEquals(SeatType.A.price + SeatType.B.price, totalPrice)
    }

    @Test
    fun `setSeatSelectType 메서드가 적절한 SeatSelectType을 설정하는지 확인한다`() {
        // Given
        val movieSeats = MovieSeats(ticketCount = 5)
        movieSeats.userSeats.add(MovieSeat("A1", SeatType.A))

        // When
        movieSeats.setSeatSelectType(MovieSeat("A1", SeatType.A))

        // Then
        assertEquals(SeatSelectType.REMOVE, movieSeats.seatSelectType)
    }

    @Test
    fun `addSeat 메서드가 좌석을 추가하는지 확인한다`() {
        // Given
        val movieSeats = MovieSeats(ticketCount = 5)
        val seat = MovieSeat("A1", SeatType.A)

        // When
        movieSeats.addSeat(seat)

        // Then
        assertEquals(1, movieSeats.userSeats.size)
        assertEquals(seat, movieSeats.userSeats.first())
    }

    @Test
    fun `deleteSeat 메서드가 좌석을 삭제하는지 확인한다`() {
        // Given
        val seatA = MovieSeat("A1", SeatType.A)
        val seatB = MovieSeat("B2", SeatType.B)
        val seats = arrayListOf(seatA, seatB)
        val movieSeats = MovieSeats(ticketCount = 5)
        movieSeats.userSeats.addAll(seats)

        // When
        movieSeats.deleteSeat(seatA)

        // Then
        assertEquals(1, movieSeats.userSeats.size)
        assertEquals(seatB, movieSeats.userSeats.first())
    }

    @Test
    fun `updateSeatSelectType 메서드가 적절한 SeatSelectType을 설정하는지 확인한다`() {
        // Given
        val movieSeats = MovieSeats(ticketCount = 5)
        movieSeats.userSeats.addAll(listOf(MovieSeat("A1", SeatType.A), MovieSeat("B2", SeatType.B)))

        // When
        movieSeats.updateSeatSelectType()

        // Then
        assertEquals(SeatSelectType.ADD, movieSeats.seatSelectType)
    }
}
