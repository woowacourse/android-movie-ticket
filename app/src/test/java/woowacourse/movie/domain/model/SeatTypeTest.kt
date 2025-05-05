package woowacourse.movie.domain.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class SeatTypeTest {
    @Test
    fun `5행은 A등급이다`() {
        // given
        val row = 5

        // when
        val seatType = SeatType.from(row)

        // then
        assertThat(seatType).isEqualTo(SeatType.RANK_A)
    }

    @Test
    fun `3, 4행은 S등급이다`() {
        // given
        val row1 = 3
        val row2 = 4

        // when
        val seatType1 = SeatType.from(row1)
        val seatType2 = SeatType.from(row2)

        // then
        assertThat(seatType1).isEqualTo(SeatType.RANK_S)
        assertThat(seatType2).isEqualTo(SeatType.RANK_S)
    }

    @Test
    fun `1, 2행은 B등급이다`() {
        // given
        val row1 = 1
        val row2 = 2

        // when
        val seatType1 = SeatType.from(row1)
        val seatType2 = SeatType.from(row2)

        // then
        assertThat(seatType1).isEqualTo(SeatType.RANK_B)
        assertThat(seatType2).isEqualTo(SeatType.RANK_B)
    }
}
