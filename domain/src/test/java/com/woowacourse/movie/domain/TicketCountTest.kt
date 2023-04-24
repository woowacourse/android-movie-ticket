package com.woowacourse.movie.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class TicketCountTest {
    @Test
    fun `티켓 개수가 음수면 예외가 발생한다`() {
        assertThrows<IllegalArgumentException> { TicketCount(0) }
    }

    @Test
    fun `티켓 개수 1개에서 1개를 더 증가시킬 수 있다`() {
        var ticketCount = TicketCount(1)
        ++ticketCount
        assertThat(ticketCount.count).isEqualTo(2)
    }

    @Test
    fun `티켓 개수 2개에서 1개를 감소시킬 수 있다`() {
        var ticketCount = TicketCount(2)
        --ticketCount
        assertThat(ticketCount.count).isEqualTo(1)
    }

    @Test
    fun `티켓 개수 1개에서 1개를 감소시켜도 개수는 1개이다`() {
        var ticketCount = TicketCount(1)
        --ticketCount
        assertThat(ticketCount.count).isEqualTo(1)
    }
}
