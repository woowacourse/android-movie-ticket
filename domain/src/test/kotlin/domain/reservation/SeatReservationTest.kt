package domain.reservation

import domain.movie.MovieName
import domain.payment.PaymentAmount
import domain.seat.ScreeningSeat
import domain.seat.SeatColumn
import domain.seat.SeatRow
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import java.time.LocalDateTime

class SeatReservationTest {

    private lateinit var seatReservation: SeatReservation

    @Before
    fun setUp() {
        seatReservation = SeatReservation(
            movieName = MovieName("해리포터"),
            screeningTime = LocalDateTime.of(1999, 10, 6, 22, 0),
            seatCount = TicketCount(3)
        )
    }

    @Test
    fun `좌석_선택에_성공하면_선택된_좌석_목록에_추가된다`() {
        // given
        seatReservation.selectSeat(ScreeningSeat(SeatRow.A, SeatColumn.FIRST))

        // when
        val selectedSeats: List<ScreeningSeat> = seatReservation.selectedSeats

        // then
        val expected = listOf(ScreeningSeat(SeatRow.A, SeatColumn.FIRST))

        assertEquals(selectedSeats, expected)
    }

    @Test(expected = IllegalStateException::class)
    fun `티켓수만큼_좌석_선택을_하지_않고_좌석_선택을_완료하게되면_예외가_발생한다`() {
        seatReservation.selectingComplete()
    }

    @Test
    fun `좌석_취소에_성공하면_선택된_좌석_목록에서_삭제된다`() {
        // given
        seatReservation.selectSeat(ScreeningSeat(SeatRow.A, SeatColumn.FIRST))

        // when
        seatReservation.cancelSeat(ScreeningSeat(SeatRow.A, SeatColumn.FIRST))
        val selectedSeats: List<ScreeningSeat> = seatReservation.selectedSeats

        // then
        val expected: List<ScreeningSeat> = listOf()

        assertEquals(selectedSeats, expected)
    }

    @Test
    fun `선택한_좌석들의_총_가격을_반환한다`() {
        // given
        seatReservation.selectSeat(ScreeningSeat(SeatRow.A, SeatColumn.FIRST))
        seatReservation.selectSeat(ScreeningSeat(SeatRow.B, SeatColumn.FIRST))
        seatReservation.selectSeat(ScreeningSeat(SeatRow.E, SeatColumn.FIRST))

        // when
        val totalPayment: PaymentAmount = seatReservation.getTotalPaymentAmount()

        // then
        val expected = PaymentAmount(32000)

        assertEquals(expected, totalPayment)
    }
}
