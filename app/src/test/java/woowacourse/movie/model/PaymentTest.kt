package woowacourse.movie.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import woowacourse.movie.model.Payment.Companion.TICKET_PRICE

class PaymentTest {
    @Test
    fun `count값이 들어오면 지불금액을 구할 수 있다`() {
        val payment = Payment()

        assertThat(payment.price(3)).isEqualTo(3 * TICKET_PRICE)
    }
}
