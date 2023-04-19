package discount

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import payment.PaymentAmount
import java.time.LocalDateTime

internal class EarlyNightDiscountTest {

    private lateinit var earlyNightDiscount: EarlyNightDiscount

    @BeforeEach
    fun setUp() {
        earlyNightDiscount = EarlyNightDiscount()
    }

    @Test
    fun `11시_이전_혹은_20시_이후가_아닌_경우_할인이_적용되지_않는다`() {
        val paymentAmount = PaymentAmount(13000)
        val resultDiscountedPaymentAmount = earlyNightDiscount.getPaymentAmountResult(
            paymentAmount,
            LocalDateTime.of(2023, 4, 13, 15, 0)
        )
        val expectedDiscountedPaymentAmount = PaymentAmount(13000)

        assertThat(resultDiscountedPaymentAmount).isEqualTo(expectedDiscountedPaymentAmount)
    }

    @Test
    fun `11시_이전인_경우_2000원_할인이_적용된다`() {
        val paymentAmount = payment.PaymentAmount(13000)
        val resultDiscountedPaymentAmount = earlyNightDiscount.getPaymentAmountResult(
            paymentAmount,
            LocalDateTime.of(2023, 4, 13, 10, 59)
        )
        val expectedDiscountedPaymentAmount = payment.PaymentAmount(11000)

        assertThat(resultDiscountedPaymentAmount).isEqualTo(expectedDiscountedPaymentAmount)
    }

    @Test
    fun `20시_이후인_경우_2000원_할인이_적용된다`() {
        val paymentAmount = PaymentAmount(13000)
        val resultDiscountedPaymentAmount = earlyNightDiscount.getPaymentAmountResult(
            paymentAmount,
            LocalDateTime.of(2023, 4, 13, 20, 0)
        )
        val expectedDiscountedPaymentAmount = PaymentAmount(11000)

        assertThat(resultDiscountedPaymentAmount).isEqualTo(expectedDiscountedPaymentAmount)
    }
}
