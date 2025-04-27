package woowacourse.movie.domain.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class SeatsTest {
    @Test
    fun `좌석을 추가할 수 있다`() {
        // given
        val seats = Seats()
        seats.add(Seat(1, 1))
        val actual = seats.size

        // when
        val expected = 1

        // then
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `이미 점유된 좌석인지 알 수 있다`() {
        // given
        val targetSeat = Seat(1, 1)
        val seats = Seats().apply { add(targetSeat) }
        val actual = seats.contains(targetSeat)

        // when
        val expected = true

        // then
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `좌석을 선택 취소할 수 있다`() {
        // given
        val targetSeat = Seat(1, 1)
        val seats =
            Seats().apply {
                add(targetSeat)
                remove(targetSeat)
            }
        val actual = seats.contains(targetSeat)

        // when
        val expected = false

        // then
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `좌석 가격의 총합을 알 수 있다`() {
        // given
        val seats =
            Seats().apply {
                add(Seat(1, 2, TicketType.B_GRADE))
                add(Seat(3, 3, TicketType.S_GRADE))
                add(Seat(5, 5, TicketType.A_GRADE))
            }
        val actual = seats.totalPrice()

        // when
        val expected =
            TicketType.B_GRADE.price + TicketType.S_GRADE.price + TicketType.A_GRADE.price

        // then
        assertThat(actual).isEqualTo(expected)
    }
}
