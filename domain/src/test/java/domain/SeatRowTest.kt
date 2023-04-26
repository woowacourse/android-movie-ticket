package domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class SeatRowTest {

    @ParameterizedTest
    @ValueSource(ints = [1, 2])
    fun `1행, 2행이면 B등급이 나온다`(value: Int) {
        val seatRow = SeatRow(value)
        assertThat(seatRow.getGrade()).isEqualTo(Grade.B)
    }

    @ParameterizedTest
    @ValueSource(ints = [3, 4])
    fun `3행, 4행이면 S등급이 나온다`(value: Int) {
        val seatRow = SeatRow(value)
        assertThat(seatRow.getGrade()).isEqualTo(Grade.S)
    }

    @Test
    fun `5행이면 A등급이 나온다`() {
        val seatRow = SeatRow(5)
        assertThat(seatRow.getGrade()).isEqualTo(Grade.A)
    }
}
