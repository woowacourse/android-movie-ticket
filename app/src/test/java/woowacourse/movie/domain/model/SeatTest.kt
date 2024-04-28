package woowacourse.movie.domain.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test


class SeatTest {
    @Test
    fun `좌석 생성`() {
        // given
        val seat = Seat("A", 1, SeatGrade.S)

        // when
        val row = seat.row
        val column = seat.number
        val seatGrade = seat.seatGrade

        // then
        assertThat(row).isEqualTo("A")
        assertThat(column).isEqualTo(1)
        assertThat(seatGrade).isEqualTo(SeatGrade.S)
    }
    
    @Test
    fun `좌석 이름 생성`() {
        // given
        val seat = Seat("A", 1, SeatGrade.S)

        // when
        val seatName = seat.toString()

        // then
        assertThat(seatName).isEqualTo("A1")
    }
    
    @Test
    fun `좌석 금액 판단`() {
        // given
        val seat = Seat("B", 2, SeatGrade.B)

        // when
        val price = seat.seatGrade.price
        
        // then
        assertThat(price).isEqualTo(10000)
    }
    
    @Test
    fun `좌석 금액 판단2`() {
        // given
        val seat = Seat("C", 3, SeatGrade.S)

        // when
        val price = seat.seatGrade.price
        
        // then
        assertThat(price).isEqualTo(15000)
    }
}
