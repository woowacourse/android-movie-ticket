package woowacourse.movie.domain.model.reservation.seat

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class SeatTest {
    @ParameterizedTest
    @CsvSource("0,2,A_RANK", "3,4,B_RANK", "5,2,S_RANK")
    fun `좌석은 행, 열의 위치 값과 등급을 가지고 있다`(
        givenRow: Int,
        givenCol: Int,
        givenRank: SeatRank,
    ) {
        val seat = Seat(givenRow, givenCol, givenRank)

        assertThat(seat.row).isEqualTo(givenRow)
        assertThat(seat.col).isEqualTo(givenCol)
        assertThat(seat.rank).isNotNull()
    }

    @Test
    fun `좌석의 등급별로 다른 금액을 가지고 있다`() {
        val row = 2
        val col = 2
        val bSeat = Seat(row, col, SeatRank.B_RANK)
        val aSeat = Seat(row, col, SeatRank.A_RANK)
        val sSeat = Seat(row, col, SeatRank.S_RANK)

        assertThat(bSeat.rank.price).isNotEqualTo(aSeat.rank.price)
        assertThat(aSeat.rank.price).isNotEqualTo(sSeat.rank.price)
        assertThat(sSeat.rank.price).isNotEqualTo(bSeat.rank.price)
    }
}
