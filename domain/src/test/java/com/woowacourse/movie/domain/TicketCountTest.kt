package com.woowacourse.movie.domain

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class TicketCountTest {
    @Test
    fun `티켓 개수가 1장 미만이면 예외가 발생한다`() {
        assertThrows<IllegalArgumentException> { TicketCount(0) }
    }
}
