package woowacourse.movie.domain.model

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import woowacourse.movie.presentation.seat.model.SeatSelectType

class MovieSeatsTest {

    @Test
    fun `getSeatPrice_메서드가_올바른_가격을_반환하는지_확인한다`() {
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
    fun `setSeatSelectType_메서드가_적절한_SeatSelectType을_설정하는지_확인한다`() {
        // Given
        val movieSeats = MovieSeats(ticketCount = 5)
        movieSeats.userSeats.add(MovieSeat("A1", SeatType.A))

        // When
        movieSeats.setSeatSelectType(MovieSeat("A1", SeatType.A))

        // Then
        assertEquals(SeatSelectType.REMOVE, movieSeats.seatSelectType)
    }

    @Test
    fun `좌석을_선택하면_선택된_좌석_리스트에_추가된다`() {
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
    fun `선택된_좌석을_재선택하면_선택이_해제된다`() {
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
    fun `updateSeatSelectType_메서드가_적절한_SeatSelectType을_설정하는지_확인한다`() {
        // Given
        val movieSeats = MovieSeats(ticketCount = 5)
        movieSeats.userSeats.addAll(listOf(MovieSeat("A1", SeatType.A), MovieSeat("B2", SeatType.B)))

        // When
        movieSeats.updateSeatSelectType()

        // Then
        assertEquals(SeatSelectType.ADD, movieSeats.seatSelectType)
    }
}
