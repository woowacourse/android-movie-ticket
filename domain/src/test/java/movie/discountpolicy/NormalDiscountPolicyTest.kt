package movie.discountpolicy

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.time.LocalDate
import java.time.LocalTime

class NormalDiscountPolicyTest {
    @Test
    fun `10일, 10시라면 무비데이 할인, 조조 할인이 순서대로 적용되어 11_000원에서 7_900원이 된다`() {
        // given
        val normalDiscountPolicy = NormalDiscountPolicy(
            LocalDate.of(2023, 4, 10),
            LocalTime.of(10, 0),
        )
        val actual = 7_900

        // then
        val expected = normalDiscountPolicy.getDiscountPrice(11_000)

        // when
        assertThat(expected).isEqualTo(actual)
    }
}
