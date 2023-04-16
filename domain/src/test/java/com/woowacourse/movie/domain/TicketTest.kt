package com.woowacourse.movie.domain

import com.woowacourse.movie.domain.policy.DiscountDecorator
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class TicketTest {
    @Test
    fun `티켓은 1장 이상이다`() {
        assertThrows<IllegalArgumentException> { Ticket(0) }
    }

    @Test
    fun `티켓을 하나 감소한다`() {
        var ticket = Ticket(5)
        val actual = --ticket
        val expected = Ticket(4)

        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `티켓을 하나 증가한다`() {
        var ticket = Ticket(5)
        val actual = ++ticket
        val expected = Ticket(6)

        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `티켓이 두 개일 때, 무비데이고 조조 시간대면 티켓당 10% 할인과 2_000원 할인이 적용된다`() {
        val ticket = Ticket(2)
        val movieDate = MovieDate(2023, 4, 10)
        val movieTime = MovieTime(9, 0)

        val actual = ticket.calculateTotalPrice(DiscountDecorator(movieDate, movieTime))
        val expected = 19_400

        assertThat(actual).isEqualTo(expected)
    }
}
