package movie.discountpolicy

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import java.time.LocalTime

class EveningDiscountPolicyTest {
    @Test
    fun `오후 8시 이후면 야간 할인이 적용되며 할인금액은 2_000원이다`() {
        // given
        val eveningPolicy = EveningDiscountPolicy(
            LocalTime.of(21, 0),
        )
        val actual = 2_000

        // when
        val expected = eveningPolicy.getDiscountPrice(10_000)

        // then
        Assertions.assertThat(expected).isEqualTo(actual)
    }

    @Test
    fun `오후 8시 이전이면 야간 할인이 적용되지 않아 할인금액은 0원이다`() {
        // given
        val eveningPolicy = EveningDiscountPolicy(
            LocalTime.of(16, 0),
        )
        val actual = 0

        // when
        val expected = eveningPolicy.getDiscountPrice(10_000)

        // then
        Assertions.assertThat(expected).isEqualTo(actual)
    }
}
