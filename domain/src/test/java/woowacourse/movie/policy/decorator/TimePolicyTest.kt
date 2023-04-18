package woowacourse.movie.policy.decorator

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource
import woowacourse.movie.policy.DefaultPolicy
import woowacourse.movie.policy.Policy
import java.time.LocalDateTime

class TimePolicyTest {

    @ValueSource(ints = [9, 10, 11])
    @ParameterizedTest
    fun `조조인 경우 티켓이 2000원 할인돼 11000원이 된다`(hour: Int) {
        val dateTime = LocalDateTime.of(2024, 3, 1, hour, 0)
        val policy: Policy = DefaultPolicy(dateTime)
        val expected = 11_000

        val actual = TimePolicy(policy).cost()

        assertThat(actual).isEqualTo(expected)
    }

    @ValueSource(ints = [20, 21, 22, 23])
    @ParameterizedTest
    fun `심야인 경우 티켓이 2000원 할인돼 11000원이 된다`(hour: Int) {
        val dateTime = LocalDateTime.of(2024, 3, 1, hour, 0)
        val policy: Policy = DefaultPolicy(dateTime)
        val expected = 11_000

        val actual = TimePolicy(policy).cost()

        assertThat(actual).isEqualTo(expected)
    }

    @ValueSource(ints = [12, 13, 14, 15, 16, 17, 18, 19])
    @ParameterizedTest
    fun `조조와 야간이 아닌 경우 티켓은 정가인 13000원이다`(hour: Int) {
        val dateTime = LocalDateTime.of(2024, 3, 1, hour, 0)
        val policy: Policy = DefaultPolicy(dateTime)
        val expected = 13_000

        val actual = TimePolicy(policy).cost()

        assertThat(actual).isEqualTo(expected)
    }
}
