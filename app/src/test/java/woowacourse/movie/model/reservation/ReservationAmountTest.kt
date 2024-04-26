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
    fun `예매 금액 10,000원에서 15,000원이 추가되면 25,000원이다`() {
        // given
        val reservationAmount = ReservationAmount(100_00)

        // when
        val actual = reservationAmount + ReservationAmount(15_000)

        // then
        assertThat(actual.amount).isEqualTo(25_000)
    }

    @Test
    fun `예매 금액 25,000원에서 10,000원이 감소되면 15,000원이다`() {
        // given
        val reservationAmount = ReservationAmount(25_000)

        // when
        val actual = reservationAmount - ReservationAmount(10_000)

        // then
        assertThat(actual.amount).isEqualTo(15_000)
    }
}
