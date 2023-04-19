package woowacourse.movie.policy.decorator

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource
import woowacourse.movie.policy.DefaultPolicy
import woowacourse.movie.policy.Policy
import woowacourse.movie.ticket.Position
import woowacourse.movie.ticket.Seat
import woowacourse.movie.ticket.SeatRank
import woowacourse.movie.ticket.Ticket
import java.time.LocalDateTime

class DatePolicyTest {
    @ValueSource(ints = [10, 20, 30])
    @ParameterizedTest
    fun `무비데이인 경우 10000원에서 10퍼센트 할인돼 9000원이 된다`(day: Int) {
        // given : 10, 20, 30일은 무비데이다.
        val dateTime = LocalDateTime.of(2024, 3, day, 17, 0)
        val ticket = Ticket(0, dateTime, Seat(SeatRank.B, Position(1, 1)))
        val policy: Policy = DefaultPolicy(ticket)
        val expected = 9_000

        // when
        val actual = DatePolicy(policy).cost()

        // then
        assertThat(actual).isEqualTo(expected)
    }

    @ValueSource(ints = [1, 9, 11, 19, 21, 29, 31])
    @ParameterizedTest
    fun `무비데이가 아닌 경우 티켓은 정가인 10000원이다`(day: Int) {
        // given
        val dateTime = LocalDateTime.of(2024, 3, day, 17, 0)
        val ticket = Ticket(0, dateTime, Seat(SeatRank.B, Position(1, 1)))
        val policy: Policy = DefaultPolicy(ticket)
        val expected = 10_000

        // when
        val actual = DatePolicy(policy).cost()

        // then
        assertThat(actual).isEqualTo(expected)
    }
}
