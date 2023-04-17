package movie.discountpolicy

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource
import java.time.LocalDate

class MovieDayDiscountPolicyTest {
    @ParameterizedTest
    @ValueSource(ints = [10, 20, 30])
    fun `{0}일이면 무비데이 할인이 적용되어 10_000원에서 10프로 할인되며 할인금액은 1000원이다`(day: Int) {
        // given
        val movieDayPolicy = MovieDayDiscountPolicy(
            LocalDate.of(2023, 4, day),
        )
        val actual = 1_000

        // when
        val expected = movieDayPolicy.getDiscountPrice(10_000)

        // then
        assertThat(expected).isEqualTo(actual)
    }

    @Test
    fun `무비데이가 아니면 할인이 적용되지 않으며 할인금액은 0원이다`() {
        // given
        val movieDayPolicy = MovieDayDiscountPolicy(
            LocalDate.of(2023, 4, 11),
        )
        val actual = 0

        // when
        val expected = movieDayPolicy.getDiscountPrice(10_000)

        // then
        assertThat(expected).isEqualTo(actual)
    }
}
