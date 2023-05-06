package woowacourse.movie.domain.discount

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.time.LocalDateTime

internal class DiscountApplicatorTest {

    @Test
    fun `할인을 적용하면 원래 금액에 모든 할인 정책을 우선순위에 따라 적용한 금액을 반환한다`() {
        val screeningDateTime = LocalDateTime.of(2024, 3, 10, 10, 0)
        val initFee = Money(10_000)

        val actual = DiscountApplicator.applyDiscount(screeningDateTime, initFee)

        assertThat(actual).isEqualTo(Money(7_000))
    }
}
