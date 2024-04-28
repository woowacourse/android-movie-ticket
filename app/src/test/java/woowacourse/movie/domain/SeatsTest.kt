package woowacourse.movie.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import woowacourse.movie.domain.seat.Seat
import woowacourse.movie.domain.seat.SeatGrade
import woowacourse.movie.domain.seat.Seats

class SeatsTest {
    @Test
    fun `seatList의 초기 값은 0 이다`() {
        val seats = Seats()
        assertThat(seats.seatList.size).isEqualTo(0)
    }

    @Test
    fun `seat를 추가할 수 있으며, 추가하면 seatList의 크기는 1 증가한다`() {
        val seats = Seats()

        seats.add(SEAT_GRADE_S)

        assertThat(seats.seatList.size).isEqualTo(1)
    }

    @Test
    fun `가지고 있는 Seat의 금액의 합을 반환할 수 있으며, 초기 값은 0 이다`() {
        val seats = Seats()
        assertThat(seats.totalPrice).isEqualTo(0)
    }

    @Test
    fun `가지고 있는 Seat의 금액의 합을 반환할 수 있다`() {
        val seats = Seats()

        seats.add(SEAT_GRADE_S)
        seats.add(SEAT_GRADE_A)
        seats.add(SEAT_GRADE_B)

        assertThat(seats.totalPrice).isEqualTo(
            PRICE_GRADE_A +
            PRICE_GRADE_B +
            PRICE_GRADE_S
        )
    }

    @Test
    fun `seat 정보가 seatList에 없는 경우, delete 를 하더라도 seatList의 크기는 변하지 않는다`() {
        val seats = Seats()

        seats.add(SEAT_GRADE_S)
        val size = seats.seatList.size

        seats.delete(Seat("A", 2, SeatGrade.A))
        assertThat(seats.seatList.size).isEqualTo(size)
    }

    @Test
    fun `특정 seat 가 seatList에 들어있는 경우, 삭제시 seatList 의 크기는 1 감소한다`() {
        val seats = Seats()

        seats.add(SEAT_GRADE_S)
        val size = seats.seatList.size

        seats.delete(SEAT_GRADE_S)
        assertThat(seats.seatList.size).isEqualTo(size-1)
    }

    @Test
    fun `row 와 col 값으로 좌석을 삭제할 수 있으며, seatList 에 해당 seat 가 있다면, 삭제시 seatList 의 크기는 1 감소한다`() {
        val seats = Seats()

        seats.add(SEAT_GRADE_S)
        val size = seats.seatList.size

        seats.delete("A", 1)
        assertThat(seats.seatList.size).isEqualTo(size-1)
    }


    companion object {
        private val SEAT_GRADE_S = Seat("A", 1, SeatGrade.S)
        private val SEAT_GRADE_A = Seat("A", 1, SeatGrade.A)
        private val SEAT_GRADE_B = Seat("A", 1, SeatGrade.B)

        private const val PRICE_GRADE_S = 15000
        private const val PRICE_GRADE_A = 12000
        private const val PRICE_GRADE_B = 10000
    }
}