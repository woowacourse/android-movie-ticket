package woowacourse.movie.policy

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import org.junit.jupiter.params.provider.ValueSource
import woowacourse.movie.ticket.Position
import woowacourse.movie.ticket.Seat
import woowacourse.movie.ticket.SeatRank
import woowacourse.movie.ticket.Ticket
import java.time.LocalDateTime

class DiscountAdapterTest {
    @CsvSource(value = ["10,10,7000", "10,22,7000"])
    @ParameterizedTest
    fun `무비데이이고 (조조 혹은 야간)이고 B등급 좌석이면 10000원에서 10퍼센트 할인되고 2000원 할인되어 7000원이다`(
        day: Int,
        hour: Int,
        expected: Int,
    ) {
        // given
        val dateTime = LocalDateTime.of(2024, 1, day, hour, 0)
        val ticket = Ticket(0, dateTime, Seat(SeatRank.B, Position(1, 1)))

        // when
        val actual = TicketPriceAdapter().getPayment(ticket)

        // then
        assertThat(actual).isEqualTo(expected)
    }

    @ValueSource(ints = [10, 20, 30])
    @ParameterizedTest
    fun `무비데이고 할인 시간이 아니고 B등급 좌석이면 10000원에서 10퍼센트 할인돼 9000원이 된다`(day: Int) {
        // given
        val dateTime = LocalDateTime.of(2024, 3, day, 17, 0)
        val ticket = Ticket(0, dateTime, Seat(SeatRank.B, Position(1, 1)))
        val expected = 9_000

        // when
        val actual = TicketPriceAdapter().getPayment(ticket)

        // then
        assertThat(actual).isEqualTo(expected)
    }

    @ValueSource(ints = [9, 10, 11, 20, 21, 22, 23])
    @ParameterizedTest
    fun `할인 시간이고 무비데이가 아니고 B등급 좌석이면 2000원 할인돼 8000원이 된다`(hour: Int) {
        // given
        val dateTime = LocalDateTime.of(2024, 3, 1, hour, 0)
        val ticket = Ticket(0, dateTime, Seat(SeatRank.B, Position(1, 1)))
        val expected = 8_000

        // when
        val actual = TicketPriceAdapter().getPayment(ticket)

        // then
        assertThat(actual).isEqualTo(expected)
    }
}
