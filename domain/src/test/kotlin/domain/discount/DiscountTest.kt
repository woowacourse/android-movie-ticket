package domain.discount

import domain.payment.PaymentAmount
import junit.framework.TestCase.assertEquals
import org.junit.Before
import org.junit.Test
import java.time.LocalDateTime

internal class DiscountTest {

    lateinit var discount: MovieDiscountEvent

    @Before
    fun setUp() {
        discount = MovieDiscountEvent()
    }

    @Test
    fun `10일_20일_30일임과_동시에_11시_이전_이거나_20시_이후인_경우에는_무비데이_할인이_먼저_적용된다`() {
        val paymentAmount = PaymentAmount(13000)
        val resultDiscountedPaymentAmount =
            discount.discount(
                paymentAmount,
                LocalDateTime.of(2023, 10, 10, 10, 0)
            )
        val expectedDiscountedPaymentAmount = domain.payment.PaymentAmount(9700)

        assertEquals(resultDiscountedPaymentAmount, expectedDiscountedPaymentAmount)
    }
}
