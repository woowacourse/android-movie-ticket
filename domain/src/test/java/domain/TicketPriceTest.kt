package woowacourse.movie.domain

import domain.TicketPrice
import domain.policy.MovieDayDiscountPolicy
import domain.policy.TimeDiscountPolicy
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.time.LocalDateTime

class TicketPriceTest {

    val ticketPrice = TicketPrice(
        listOf(
            MovieDayDiscountPolicy(),
            TimeDiscountPolicy(),
        ),
    )

    @Test
    fun `인원 수가 3명일 때 할인이 적용되지 않은 티켓 값은 39000원이다`() {
        val totalTicketPrice = ticketPrice.applyPolicy(LocalDateTime.of(2024, 4, 1, 15, 10)) * 3
        val expect = 39000

        assertEquals(totalTicketPrice.price, expect)
    }

    @Test
    fun `인원 수가 3명일 때 조조 할인이 적용된 티켓 값은 33000원이다`() {
        val totalTicketPrice = ticketPrice.applyPolicy(LocalDateTime.of(2024, 4, 1, 9, 10)) * 3
        val expect = 33000

        assertEquals(totalTicketPrice.price, expect)
    }

    @Test
    fun `인원 수가 3명일 때 무비데이 할인이 적용된 티켓 값은 3000원이다`() {
        val totalTicketPrice = ticketPrice.applyPolicy(LocalDateTime.of(2024, 4, 10, 15, 10)) * 3
        val expect = 35100

        assertEquals(totalTicketPrice.price, expect)
    }

    @Test
    fun `인원 수가 3명일 때 무비데이 할인과 야간 할인이 적용된 티켓 값은 33000원이다`() {
        val totalTicketPrice = ticketPrice.applyPolicy(LocalDateTime.of(2024, 4, 10, 21, 10)) * 3
        val expect = 29100

        assertEquals(totalTicketPrice.price, expect)
    }
}
