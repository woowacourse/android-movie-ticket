package discount

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import woowacourse.movie.domain.discount.Discount
import woowacourse.movie.domain.discount.EarlyNightDiscount
import woowacourse.movie.domain.discount.MovieDayDiscount
import woowacourse.movie.domain.payment.PaymentAmount
import java.time.LocalDateTime

internal class DiscountTest {

    private lateinit var discount: Discount
    private val paymentAmount: PaymentAmount = PaymentAmount(13000)

    @BeforeEach
    fun setUp() {
        discount = Discount(MovieDayDiscount(), EarlyNightDiscount())
    }

    @Test
    fun `10일_20일_30일임과_동시에_11시_이전_이거나_20시_이후인_경우에는_무비데이_할인이_먼저_적용된다`() {
        val resultDiscountedPaymentAmount =
            discount.getPaymentAmountResult(
                paymentAmount,
                LocalDateTime.of(2023, 10, 10, 10, 0)
            )
        val expectedDiscountedPaymentAmount = PaymentAmount(9700)

        assertThat(resultDiscountedPaymentAmount).isEqualTo(expectedDiscountedPaymentAmount)
    }
}
