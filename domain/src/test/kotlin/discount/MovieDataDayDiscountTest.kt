package discount

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import payment.PaymentAmount
import java.time.LocalDateTime

internal class MovieDataDayDiscountTest {

    private lateinit var movieDayDiscount: MovieDayDiscount
    private val paymentAmount: PaymentAmount = PaymentAmount(13000)

    @BeforeEach
    fun setUp() {
        movieDayDiscount = MovieDayDiscount()
    }

    @Test
    fun `10일인_경우_10%_할인이_적용된다`() {
        val resultDiscountedPaymentAmount = movieDayDiscount.getPaymentAmountResult(
            paymentAmount,
            LocalDateTime.of(2023, 4, 10, 13, 0)
        )
        val expectedDiscountedPaymentAmount = PaymentAmount(11700)

        assertThat(resultDiscountedPaymentAmount).isEqualTo(expectedDiscountedPaymentAmount)
    }

    @Test
    fun `20일인_경우_10%_할인이_적용된다`() {
        val resultDiscountedPaymentAmount = movieDayDiscount.getPaymentAmountResult(
            paymentAmount,
            LocalDateTime.of(2023, 4, 20, 13, 0)
        )
        val expectedDiscountedPaymentAmount = PaymentAmount(11700)

        assertThat(resultDiscountedPaymentAmount).isEqualTo(expectedDiscountedPaymentAmount)
    }

    @Test
    fun `30일_경우_10%_할인이_적용된다`() {
        val resultDiscountedPaymentAmount = movieDayDiscount.getPaymentAmountResult(
            paymentAmount,
            LocalDateTime.of(2023, 4, 30, 13, 0)
        )
        val expectedDiscountedPaymentAmount = PaymentAmount(11700)

        assertThat(resultDiscountedPaymentAmount).isEqualTo(expectedDiscountedPaymentAmount)
    }

    @Test
    fun `10일_20일_30일이_아닌_경우_할인이_적용되지_않는다`() {
        val paymentAmount = payment.PaymentAmount(13000)
        val resultDiscountedPaymentAmount = movieDayDiscount.getPaymentAmountResult(
            paymentAmount,
            LocalDateTime.of(2023, 4, 13, 15, 0)
        )
        val expectedDiscountedPaymentAmount = PaymentAmount(13000)

        assertThat(resultDiscountedPaymentAmount).isEqualTo(expectedDiscountedPaymentAmount)
    }
}
