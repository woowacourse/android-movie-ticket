package payment

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows
import woowacourse.movie.domain.payment.PaymentAmount

class PaymentAmountTest {

    @Test
    fun 납부할_금액이_0원_이상일_때_객체를_생성한다() {
        assertDoesNotThrow { PaymentAmount(0) }
    }

    @Test
    fun 납부할_금액은_0원_미만이면_예외가_발생한다() {
        assertThrows<IllegalArgumentException> { PaymentAmount(-1000) }
    }
}
