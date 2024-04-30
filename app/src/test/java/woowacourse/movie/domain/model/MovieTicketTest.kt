package woowacourse.movie.domain.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.time.LocalDateTime


class MovieTicketTest {
    @Test
    fun `영화티켓 생성`() {
        // given
        val screeningDateTime = LocalDateTime.of(2024, 4, 1, 10, 0)
        val movieTicket = MovieTicket(1, "해리포터", screeningDateTime, 2, Seats(listOf(Seat("A1", 1, SeatGrade.A))))
        
        // when
        val id = movieTicket.id
        val movieTitle = movieTicket.movieTitle
        val screeningDate = movieTicket.screeningDate
        val reservationCount = movieTicket.reservationCount
        val reservedSeats = movieTicket.reservationSeats
        
        // then
        assertThat(id).isEqualTo(1)
        assertThat(movieTitle).isEqualTo("해리포터")
        assertThat(screeningDate).isEqualTo(screeningDateTime)
        assertThat(reservationCount).isEqualTo(2)
        assertThat(reservedSeats.seats).containsExactly(Seat("A1", 1, SeatGrade.A))
    }
    
    @Test
    fun `영화티켓 좌석 예약`() {
        // given
        val screeningDateTime = LocalDateTime.of(2024, 4, 1, 10, 0)
        val movieTicket = MovieTicket(1, "해리포터", screeningDateTime, 2, Seats(listOf(Seat("A1", 1, SeatGrade.A))))
        val newSeats = listOf(Seat("A2", 2, SeatGrade.A), Seat("A3", 3, SeatGrade.A))
        
        // when
        val reservedMovieTicket = movieTicket.reserveSeats(newSeats)
        
        // then
        assertThat(reservedMovieTicket.reservationSeats.seats).containsExactly(Seat("A2", 2, SeatGrade.A), Seat("A3", 3, SeatGrade.A))
    }
    
    @Test
    fun `영화티켓 총 금액 계산`() {
        // given
        val screeningDateTime = LocalDateTime.of(2024, 4, 1, 10, 0)
        val movieTicket = MovieTicket(1, "해리포터", screeningDateTime, 2, Seats(listOf(Seat("A1", 1, SeatGrade.A))))
        val newSeats = listOf(Seat("A2", 2, SeatGrade.A), Seat("A3", 3, SeatGrade.A))
        val reservedMovieTicket = movieTicket.reserveSeats(newSeats)
        
        // when
        val totalPrice = reservedMovieTicket.totalPrice()
        
        // then
        assertThat(totalPrice).isEqualTo(24000)
    }
}
