package com.woowacourse.movie.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class TicketCountTest {
    @Test
    fun `티켓 개수가 1보다 작으면 예외가 발생한다`() {
        assertThrows<IllegalArgumentException> { TicketCount(0) }
    }

    @Test
    fun `티켓 개수 1개에서 1개를 더 증가시킬 수 있다`() {
        val ticketCount = TicketCount(1)

        val actual = ticketCount.inc()

        assertThat(actual.count).isEqualTo(2)
    }

    @Test
    fun `티켓 개수 2개에서 1개를 감소시킬 수 있다`() {
        val ticketCount = TicketCount(2)

        val actual = ticketCount.dec()

        assertThat(actual.count).isEqualTo(1)
    }

    @Test
    fun `티켓 개수 1개에서 1개를 감소시켜도 개수는 1개이다`() {
        val ticketCount = TicketCount(1)

        val actual = ticketCount.dec()

        assertThat(actual.count).isEqualTo(1)
    }
}
