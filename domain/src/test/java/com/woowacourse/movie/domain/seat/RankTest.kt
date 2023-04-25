package com.woowacourse.movie.domain.seat

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class RankTest {
    @ParameterizedTest
    @ValueSource(ints = [0, 1])
    fun `좌석의 행이 0~1이면 B좌석이다`(row: Int) {
        val seatPosition = SeatPosition(row = row)

        val actual = Rank.valueOf(seatPosition)
        val expected = Rank.B

        assertThat(actual).isEqualTo(expected)
    }
    @ParameterizedTest
    @ValueSource(ints = [2, 3])
    fun `좌석의 행이 2~3이면 A좌석이다`(row: Int) {
        val seatPosition = SeatPosition(row = row)

        val actual = Rank.valueOf(seatPosition)
        val expected = Rank.A

        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `좌석의 행이 4이면 S좌석이다`() {
        val seatPosition = SeatPosition(row = 4)

        val actual = Rank.valueOf(seatPosition)
        val expected = Rank.S

        assertThat(actual).isEqualTo(expected)
    }

    companion object {
        fun SeatPosition(row: Int = 0, col: Int = 0): SeatPosition = SeatPosition(Row(row), Col(col))
    }
}
