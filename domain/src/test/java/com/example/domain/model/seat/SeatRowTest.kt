package com.example.domain.model.seat

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class SeatRowTest {
    @Test
    fun `시트 행을 행의 숫자로 알 수 있다`() {
        val rowInt = 1
        val actual = SeatRow.valueOf(rowInt)
        val expected = SeatRow.A
        assertThat(actual).isEqualTo(expected)
    }
}
