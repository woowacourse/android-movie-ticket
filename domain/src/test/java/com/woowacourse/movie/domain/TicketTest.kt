package com.woowacourse.movie.domain

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
    fun `티켓이 두 장, 티켓 가격이 13_000원이면 최종 가격은 26_000원이다`() {
        val ticket = Ticket(2)
        val actual = ticket.calculatePrice(13_000)
        val expected = 26_000

        assertThat(actual).isEqualTo(expected)
    }
}
