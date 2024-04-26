package woowacourse.movie.model.reservation

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class ReservationAmountTest {
    @Test
    fun `예메 금액의 초기값은 0이다`() {
        // given
        val reservationAmount = ReservationAmount()

        // when
        val actual = reservationAmount.amount

        // then
        assertThat(actual).isEqualTo(0)
    }

    @Test
    fun `예매 금액 10000원에서 15000원이 추가되면 25000원이다`() {
        // given
        val reservationAmount = ReservationAmount(10000)

        // when
        val actual = reservationAmount + ReservationAmount(15000)

        // then
        assertThat(actual.amount).isEqualTo(25000)
    }

    @Test
    fun `예매 금액 25000원에서 10000원이 감소되면 15000원이다`() {
        // given
        val reservationAmount = ReservationAmount(25000)

        // when
        val actual = reservationAmount - ReservationAmount(10000)

        // then
        assertThat(actual.amount).isEqualTo(15000)
    }
}
