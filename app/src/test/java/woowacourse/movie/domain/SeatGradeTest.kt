package woowacourse.movie.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class SeatGradeTest {
    @ParameterizedTest
    @ValueSource(ints = [1, 2])
    fun `1, 2행은 B등급이다`(row: Int) {
        // when
        val seat = Seat(row, 1)

        // then
        assertThat(seat.grade).isEqualTo(SeatGrade.B)
    }

    @Test
    fun `B등급 좌석의 가격은 10,000원이다`() {
        // when
        val seatGrade = SeatGrade.B

        // then
        assertThat(seatGrade.price).isEqualTo(10_000)
    }

    @ParameterizedTest
    @ValueSource(ints = [3, 4])
    fun `3, 4행은 S등급이다`(row: Int) {
        // when
        val seat = Seat(row, 1)

        // then
        assertThat(seat.grade).isEqualTo(SeatGrade.S)
    }

    @Test
    fun `S등급 좌석의 가격은 15,000원이다`() {
        // when
        val seatGrade = SeatGrade.S

        // then
        assertThat(seatGrade.price).isEqualTo(15_000)
    }

    @ParameterizedTest
    @ValueSource(ints = [5])
    fun `5행은 A등급이다`(row: Int) {
        // when
        val seat = Seat(row, 1)

        // then
        assertThat(seat.grade).isEqualTo(SeatGrade.A)
    }

    @Test
    fun `A등급 좌석의 가격은 12,000원이다`() {
        // when
        val seatGrade = SeatGrade.A

        // then
        assertThat(seatGrade.price).isEqualTo(12_000)
    }
}
