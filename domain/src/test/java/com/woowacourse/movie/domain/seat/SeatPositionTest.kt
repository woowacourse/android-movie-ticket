package com.woowacourse.movie.domain.seat

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class SeatPositionTest {
    @Test
    fun `좌석 좌표의 행은 0~4행을 벗어나면 예외가 발생한다`() {
        assertThrows<IllegalArgumentException> {
            SeatPosition(row = 5)
        }
    }

    @Test
    fun `좌석 좌표의 열은 0~3열을 벗어나면 예외가 발생한다`() {
        assertThrows<IllegalArgumentException> {
            SeatPosition(col = 4)
        }
    }

    companion object {
        fun SeatPosition(row: Int = 0, col: Int = 0): SeatPosition = SeatPosition(Row(row), Col(col))
    }
}
