package com.example.domain.model.seat

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class SeatColumnTest {
    @Test
    fun `시트 열을 열의 숫자로 알 수 있다`() {
        val columnInt = 1
        val actual = SeatColumn.valueOf(columnInt)
        val expected = SeatColumn.ONE
        assertThat(actual).isEqualTo(expected)
    }
}
