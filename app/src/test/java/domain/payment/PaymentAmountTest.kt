package domain.payment

import org.junit.Test
import woowacourse.movie.domain.payment.PaymentAmount

class PaymentAmountTest {

    @Test
    fun 납부할_금액이_0원_이상일_때_객체를_생성한다() {
        PaymentAmount(0)
    }

    @Test(expected = IllegalArgumentException::class)
    fun 납부할_금액은_0원_미만이면_예외가_발생한다() {
        PaymentAmount(-1000)
    }
}
