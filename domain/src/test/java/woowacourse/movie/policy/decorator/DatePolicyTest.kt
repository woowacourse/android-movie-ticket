package woowacourse.movie.policy.decorator

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource
import woowacourse.movie.policy.DefaultPolicy
import woowacourse.movie.policy.Policy
import java.time.LocalDateTime

class DatePolicyTest {
    @ValueSource(ints = [10, 20, 30])
    @ParameterizedTest
    fun `무비데이인 경우 티켓이 10퍼센트 할인돼 11700원이 된다`(day: Int) {
        // given : 10, 20, 30일은 무비데이다.
        val dateTime = LocalDateTime.of(2024, 3, day, 17, 0)
        val policy: Policy = DefaultPolicy(dateTime)
        val expected = 11_700

        // when
        val actual = DatePolicy(policy).cost()

        // then
        assertThat(actual).isEqualTo(expected)
    }

    @ValueSource(ints = [1, 9, 11, 19, 21, 29, 31])
    @ParameterizedTest
    fun `무비데이가 아닌 경우 티켓은 정가인 13000원이다`(day: Int) {
        // given
        val dateTime = LocalDateTime.of(2024, 3, day, 17, 0)
        val policy: Policy = DefaultPolicy(dateTime)
        val expected = 13_000

        // when
        val actual = DatePolicy(policy).cost()

        // then
        assertThat(actual).isEqualTo(expected)
    }
}
