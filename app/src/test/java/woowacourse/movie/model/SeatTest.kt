package woowacourse.movie.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class SeatTest {
    @Test
    fun `좌석의 이름에 맞는 등급을 확인할 수 있다`() {
        val seat = Seat("A1")

        assertThat(seat.grade).isEqualTo(SeatGrade.B)
    }

    @Test
    fun `좌석의 이름은 비어있을 수 없다`() {
        assertThrows<NoSuchElementException> { Seat("") }
    }

    @Test
    fun `좌석의 이름이 아닌 행을 넣을 수 없다`() {
        assertThrows<IllegalArgumentException> { Seat("K1").grade }
        assertThrows<IllegalArgumentException> { Seat("O1").grade }
    }
}
