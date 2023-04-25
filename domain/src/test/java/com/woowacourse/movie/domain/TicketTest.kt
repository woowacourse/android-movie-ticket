package com.woowacourse.movie.domain

import com.woowacourse.movie.domain.seat.Col
import com.woowacourse.movie.domain.seat.Rank
import com.woowacourse.movie.domain.seat.Row
import com.woowacourse.movie.domain.seat.SeatPosition
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.time.LocalDateTime

class TicketTest {
    @Test
    fun `좌석 등급과 현재 시간을 알면 티켓 가격을 계산할 수 있다`() {
        val ticket = Ticket(0, 0)
        val currentDateTime = LocalDateTime.of(2023, 4, 11, 12, 0)

        val actual = ticket.calculatePrice(Rank.valueOf(ticket.seatPosition), currentDateTime)

        val expected = 10_000

        assertThat(actual).isEqualTo(expected)
    }

    companion object {
        fun Ticket(row: Int, col: Int): Ticket = Ticket(SeatPosition(Row(row), Col(col)))
    }
}
