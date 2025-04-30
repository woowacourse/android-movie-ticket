package woowacourse.movie.domain

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import woowacourse.movie.domain.fixture.oneByOneSeat
import woowacourse.movie.domain.fixture.oneByTowSeat
import woowacourse.movie.domain.model.seat.Seats

class SeatsTest {
    @Test
    fun `새로운 좌석이 추가된다`() {
        // when
        val seats = Seats()

        // given
        seats.addSeat(oneByOneSeat)

        // then
        assertTrue(seats.item.contains(oneByOneSeat))
    }

    @Test
    fun `선택한 좌석이 제거된다`() {
        // when
        val seats = Seats()

        // given
        seats.addSeat(oneByOneSeat)
        seats.removeSeat(oneByOneSeat)

        // then
        assertTrue(seats.item.isEmpty()) // assertTrue로 변경
    }

    @Test
    fun `B등급 좌석 두 개를 예매하면 총 가격 2만원을 반환한다`() {
        // when
        val seats = Seats()

        seats.addSeat(oneByOneSeat)
        seats.addSeat(oneByTowSeat)

        // given
        val totalPrice = seats.bookingPrice()

        // then
        assertEquals(20000, totalPrice)
    }

    @Test
    fun `선택한 예매 인원수만큼 예매하지 않았으면 참을 반환한다`() {
        // when
        val seats = Seats()
        seats.addSeat(oneByOneSeat)

        // given
        val result = seats.isNotSelectDone(2)

        // then
        assertTrue(result)
    }

    @Test
    fun `선택한 예매 인원수 만큼 예매 했으면 거짓을 반환한다`() {
        // when
        val seats = Seats()
        seats.addSeat(oneByOneSeat)
        seats.addSeat(oneByTowSeat)

        // given
        val result = seats.isNotSelectDone(2)

        // then
        assertFalse(result)
    }

    @Test
    fun `예매된 좌석이 없으면 총 가격은 0원이다`() {
        // when
        val seats = Seats()

        // given
        val totalPrice = seats.bookingPrice()

        // then
        assertEquals(0, totalPrice)
    }

    @Test
    fun `하나의 좌석만 예매하면 가격이 해당 좌석 가격으로 계산된다`() {
        // when
        val seats = Seats()
        seats.addSeat(oneByOneSeat)

        // given
        val totalPrice = seats.bookingPrice()

        // then
        assertEquals(10000, totalPrice)
    }
}
