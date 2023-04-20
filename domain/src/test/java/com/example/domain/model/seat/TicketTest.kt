package com.example.domain.model.seat

import com.example.domain.model.Money
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class TicketTest {
    @Test
    fun `티켓 좌석 정보로 랭크와 해당 티켓의 원본 가격을 알 수 있다`() {
        val ticket = Ticket(SeatPosition(2, 3))
        val actual = ticket.money
        val expected = Money(10000)
        assertThat(actual).isEqualTo(expected)
    }
}
