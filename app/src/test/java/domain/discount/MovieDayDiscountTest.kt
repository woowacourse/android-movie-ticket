package domain.discount

import org.junit.Assert
import org.junit.Before
import org.junit.Test
import woowacourse.movie.domain.discount.MovieDayDiscount
import woowacourse.movie.domain.payment.PaymentAmount
import java.time.LocalDateTime

internal class MovieDayDiscountTest {

    lateinit var movieDayDiscount: MovieDayDiscount
    val paymentAmount: PaymentAmount = PaymentAmount(13000)

    @Before
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

        Assert.assertEquals(resultDiscountedPaymentAmount, expectedDiscountedPaymentAmount)
    }

    @Test
    fun `20일인_경우_10%_할인이_적용된다`() {
        val resultDiscountedPaymentAmount = movieDayDiscount.getPaymentAmountResult(
            paymentAmount,
            LocalDateTime.of(2023, 4, 20, 13, 0)
        )
        val expectedDiscountedPaymentAmount = PaymentAmount(11700)

        Assert.assertEquals(resultDiscountedPaymentAmount, expectedDiscountedPaymentAmount)
    }

    @Test
    fun `30일_경우_10%_할인이_적용된다`() {
        val resultDiscountedPaymentAmount = movieDayDiscount.getPaymentAmountResult(
            paymentAmount,
            LocalDateTime.of(2023, 4, 30, 13, 0)
        )
        val expectedDiscountedPaymentAmount = PaymentAmount(11700)

        Assert.assertEquals(resultDiscountedPaymentAmount, expectedDiscountedPaymentAmount)
    }

    @Test
    fun `10일_20일_30일이_아닌_경우_할인이_적용되지_않는다`() {
        val paymentAmount = PaymentAmount(13000)
        val resultDiscountedPaymentAmount = movieDayDiscount.getPaymentAmountResult(
            paymentAmount,
            LocalDateTime.of(2023, 4, 13, 15, 0)
        )
        val expectedDiscountedPaymentAmount = PaymentAmount(13000)

        Assert.assertEquals(resultDiscountedPaymentAmount, expectedDiscountedPaymentAmount)
    }
}
