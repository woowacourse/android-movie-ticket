package woowacourse.movie.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class SeatsTest {
    @Test
    fun `초기의 좌석 수는 0이다`() {
        // given
        val seats = Seats()

        // then
        assertThat(seats.seats.size).isEqualTo(0)
    }

    @Test
    fun `예약하려는 좌석을 추가할 수 있다`() {
        // given
        val seats = Seats()
        val seat = Seat(1, 1)

        // when
        seats.addSeat(seat)

        // then
        assertThat(seat in seats).isTrue()
    }

    @Test
    fun `취소하려는 좌석을 삭제할 수 있다`() {
        // given
        val seats = Seats()
        val seat = Seat(2, 2)
        seats.addSeat(seat)

        // when
        seats.removeSeat(seat)

        // then
        assertThat(seat !in seats).isTrue()
    }

    @Test
    fun `좌석들의 총 가격을 계산할 수 있다`() {
        // given
        val seats = Seats()
        val seat1 = Seat(1, 1)
        val seat2 = Seat(4, 4)
        val price1 = seat1.price(Grade.A)
        val price2 = seat2.price(Grade.B)

        // when
        seats.addSeat(seat1)
        seats.addSeat(seat2)

        // then
        assertThat(seats.totalPrice()).isEqualTo(price1 + price2)
    }
}
