package movie.discountpolicy

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.time.LocalTime

class EarlyMorningDiscountPolicyTest {
    @Test
    fun `오전 10시 이전이면 조조할인이 적용되며 할인 금액은 2000원이다`() {
        // given
        val earlyMorningPolicy = EarlyMorningDiscountPolicy(
            LocalTime.of(10, 0),
        )
        val actual = 2_000

        // when
        val expected = earlyMorningPolicy.getDiscountPrice(10_000)

        // then
        assertThat(expected).isEqualTo(actual)
    }

    @Test
    fun `오전 11시 이후면 조조할인이 적용되지 않아 할인금액은 0원이다`() {
        // given
        val earlyMorningPolicy = EarlyMorningDiscountPolicy(
            LocalTime.of(12, 0),
        )
        val actual = 0

        // when
        val expected = earlyMorningPolicy.getDiscountPrice(10_000)

        // then
        assertThat(expected).isEqualTo(actual)
    }
}
