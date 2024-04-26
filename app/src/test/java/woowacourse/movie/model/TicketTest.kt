package woowacourse.movie.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.time.LocalDate

class TicketTest {
    @Test
    fun `티켓를 1장 증가시키면 매수가 1장 증가한다 `() {
        val ticket = Ticket(10)

        ticket.increaseCount()

        assertThat(ticket.count).isEqualTo(11)
    }

    @Test
    fun `티켓 1장 감소시키면 매수가 1장 감소한다 `() {
        val ticket = Ticket(10)

        ticket.decreaseCount()

        assertThat(ticket.count).isEqualTo(9)
    }

    @Test
    fun `티켓의 최소 수량은 1개이다`() {
        val ticket = Ticket(1)

        ticket.decreaseCount()

        assertThat(ticket.count).isEqualTo(1)
    }

    @Test
    fun `티켓의 최대 수량은 100개이다`() {
        val ticket = Ticket(100)

        ticket.increaseCount()

        assertThat(ticket.count).isEqualTo(100)
    }

    @Test
    fun `티켓 매수에 따라 결제 금액을 계산한다`() {
        val ticket = Ticket(3)

        val actual = ticket.calculatePrice()

        assertThat(actual).isEqualTo(39_000)
    }

    @Test
    fun `주말이면 9 ~ 23을 반환`() {
        val ticket = Ticket()
        val weekend = LocalDate.of(2024, 4, 7)

        val actual = ticket.obtainScreeningTimes(weekend)
        val expected = (9..23 step 2).toList()

        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `평일이면 10 ~ 24를 반환`() {
        val ticket = Ticket()
        val weekday = LocalDate.of(2024, 4, 8)

        val actual = ticket.obtainScreeningTimes(weekday)
        val expected = (10..24 step 2).toList()

        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `시작 날짜, 끝 날짜에 해당하는 만큼 날짜 반환`() {
        val ticket = Ticket()
        val firstScreeningDate = LocalDate.of(2023, 1, 1)
        val lastScreeningDate = LocalDate.of(2023, 1, 3)

        val actual = ticket.obtainScreeningDates(firstScreeningDate, lastScreeningDate)
        val expected =
            listOf(LocalDate.of(2023, 1, 1), LocalDate.of(2023, 1, 2), LocalDate.of(2023, 1, 3))

        assertThat(actual).isEqualTo(expected)
    }
}
