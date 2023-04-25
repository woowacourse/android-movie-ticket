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

class TimePolicyTest {

    @ValueSource(ints = [9, 10, 11])
    @ParameterizedTest
    fun `조조인 경우 티켓이 10000원에서 2000원 할인돼 8000원이 된다`(hour: Int) {
        val dateTime = LocalDateTime.of(2024, 3, 1, hour, 0)
        val ticket = Ticket(0, dateTime, Seat(SeatRank.B, Position(1, 1)))
        val policy: Policy = DefaultPolicy(ticket)
        val expected = 8_000

        val actual = TimePolicy(policy).cost()

        assertThat(actual).isEqualTo(expected)
    }

    @ValueSource(ints = [20, 21, 22, 23])
    @ParameterizedTest
    fun `심야인 경우 티켓이 10000원에서 2000원 할인돼 8000원이 된다`(hour: Int) {
        val dateTime = LocalDateTime.of(2024, 3, 1, hour, 0)
        val ticket = Ticket(0, dateTime, Seat(SeatRank.B, Position(1, 1)))
        val policy: Policy = DefaultPolicy(ticket)
        val expected = 8_000

        val actual = TimePolicy(policy).cost()

        assertThat(actual).isEqualTo(expected)
    }

    @ValueSource(ints = [12, 13, 14, 15, 16, 17, 18, 19])
    @ParameterizedTest
    fun `조조와 야간이 아닌 경우 티켓은 정가인 10000원이다`(hour: Int) {
        val dateTime = LocalDateTime.of(2024, 3, 1, hour, 0)
        val ticket = Ticket(0, dateTime, Seat(SeatRank.B, Position(1, 1)))
        val policy: Policy = DefaultPolicy(ticket)
        val expected = 10_000

        val actual = TimePolicy(policy).cost()

        assertThat(actual).isEqualTo(expected)
    }
}
