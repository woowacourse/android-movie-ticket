package woowacourse.movie.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class TicketCountTest {
    @ParameterizedTest
    @ValueSource(ints = [1, 0, -1])
    fun `티켓의 개수는 1보다 작아질 수 없다`(value: Int) {
        // given
        val count = TicketCount.of(value)

        // when
        val actual: TicketCount = count.decrement()
        val expected: TicketCount = TicketCount.of(1)

        // then
        assertThat(actual).isEqualTo(expected)
    }
}
