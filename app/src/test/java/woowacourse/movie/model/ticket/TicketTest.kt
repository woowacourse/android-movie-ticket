package woowacourse.movie.model.ticket

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import woowacourse.movie.model.result.Failure
import woowacourse.movie.model.seats.Grade
import woowacourse.movie.model.seats.Seat
import woowacourse.movie.model.seats.Seats

class TicketTest {
    @Test
    fun `플러스 버튼을 누르면 티켓 매수가 1장 증가한다 `() {
        val ticket = Ticket()

        ticket.increaseCount()

        assertThat(ticket.count).isEqualTo(2)
    }

    @Test
    fun `마이너스 버튼을 누르면 티켓 매수가 1장 감소한다 `() {
        val ticket = Ticket()

        ticket.increaseCount()
        ticket.decreaseCount()

        assertThat(ticket.count).isEqualTo(1)
    }

    @Test
    fun `티켓이 1장일 때 마이너스 버튼을 누르면 Failure가 반환된다`() {
        val ticket = Ticket()

        val actual = ticket.decreaseCount()

        assertThat(actual).isInstanceOf(Failure::class.java)
    }

    @Test
    fun `티켓이 100장일 때 플러스 버튼을 누르면 Failure가 반환된다`() {
        val ticket = Ticket()

        repeat(99) {
            ticket.increaseCount()
        }

        val actual = ticket.increaseCount()

        assertThat(actual).isInstanceOf(Failure::class.java)
    }

    @Test
    fun `선택한 좌석의 등급에 따른 가격을 합해서 총 결제 금액을 반환한다`() {
        val seats = Seats()
        seats.manageSelected(true, Seat('A', 1, Grade.B))
        seats.manageSelected(true, Seat('C', 1, Grade.S))

        val ticket = Ticket()
        val actual = ticket.calculateAmount(seats)

        assertThat(actual).isEqualTo(25_000)
    }
}
