package domain.discount

import domain.payment.PaymentAmount
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import java.time.LocalDateTime

internal class DiscountTest {

    lateinit var discount: Discount
    val paymentAmount: PaymentAmount = PaymentAmount(13000)

    @Before
    fun setUp() {
        discount = Discount()
    }

    @Test
    fun `10일_20일_30일임과_동시에_11시_이전_이거나_20시_이후인_경우에는_무비데이_할인이_먼저_적용된다`() {
        val resultDiscountedPaymentAmount =
            discount.getPaymentAmountResult(
                paymentAmount,
                LocalDateTime.of(2023, 10, 10, 10, 0)
            )
        val expectedDiscountedPaymentAmount = PaymentAmount(9700)

        assertEquals(resultDiscountedPaymentAmount, expectedDiscountedPaymentAmount)
    }
}
