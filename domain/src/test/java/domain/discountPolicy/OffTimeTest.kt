package domain.discountPolicy

import domain.Price
import domain.Seat
import domain.Ticket
import domain.seatPolicy.SeatPolicies
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.time.LocalDateTime

class OffTimeTest {
    @Test
    fun `11시 이전일 경우 2000원 할인이 적용된다`() {
        // given
        val date = LocalDateTime.of(2023, 1, 10, 10, 0)
        val ticket = Ticket(date, Seat(1, 3, SeatPolicies()), DisCountPolicies())

        // when
        val actual = OffTime().discount(ticket, Price(13_000)).value

        // then
        val expected = 11000
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `11시 이후, 20시 이전일 경우 2000원 할인이 적용되지 않는다`() {
        // given
        val date = LocalDateTime.of(2023, 1, 10, 11, 0)
        val ticket = Ticket(date, Seat(1, 3, SeatPolicies()), DisCountPolicies())

        // when
        val actual = OffTime().discount(ticket, Price(13_000)).value

        // then
        val expected = 13000
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `20시 이후 일 경우 2000원 할인이 적용된다`() {
        // given
        val date = LocalDateTime.of(2023, 1, 10, 21, 0)
        val price = Price(13000)
        val ticket = domain.Ticket(date, Seat(1, 3, SeatPolicies()), DisCountPolicies())

        // when
        val actual = OffTime().discount(ticket, price).value

        // then
        val expected = 11000
        assertThat(actual).isEqualTo(expected)
    }
}
