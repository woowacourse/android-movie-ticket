package woowacourse.movie.domain.model.reservation.seat

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class SelectedSeatsTest {
    @ParameterizedTest
    @CsvSource("1,3", "3,2", "4,0")
    fun `선택된 위치의 좌석을 추가할 수 있다`(
        row: Int,
        col: Int,
    ) {
        // given
        val selectedSeats = SelectedSeats(SeatingChart(), 3)

        // when
        selectedSeats.tryAddOrDeleteSeat(row, col)

        // then
        assertThat(selectedSeats.seats.size).isEqualTo(1)
        assertThat(selectedSeats.seats[0].row).isEqualTo(row)
        assertThat(selectedSeats.seats[0].col).isEqualTo(col)
    }

    @ParameterizedTest
    @CsvSource("1,3", "3,2", "4,0")
    fun `이미 선택한 위치의 좌석이라면 리스트에서 제거한다`(
        row: Int,
        col: Int,
    ) {
        // given
        val selectedSeats = SelectedSeats(SeatingChart(), 3)
        selectedSeats.tryAddOrDeleteSeat(row, col)

        // when
        selectedSeats.tryAddOrDeleteSeat(row, col)

        // then
        assertThat(selectedSeats.seats).isEmpty()
    }

    @ParameterizedTest
    @CsvSource("1,0,2,true", "2,2,3,false", "4,3,1,true")
    fun `선택한 좌석의 개수가 예매 인원 수와 동일한지 확인할 수 있다`(
        row: Int,
        col: Int,
        reservationCount: Int,
        expected: Boolean,
    ) {
        // given
        val selectedSeats = SelectedSeats(SeatingChart(), reservationCount)
        selectedSeats.tryAddOrDeleteSeat(0, 0)

        // when
        selectedSeats.tryAddOrDeleteSeat(row, col)

        // then
        val actual = selectedSeats.isMatchedTheCount()
        assertThat(actual).isEqualTo(expected)
    }

    @ParameterizedTest
    @CsvSource("1,0", "2,2", "4,3")
    fun `선택한 좌석의 개수가 예매 인원 수와 동일하다면 더 이상 좌석을 추가하지 않는다`(
        row: Int,
        col: Int,
    ) {
        // given
        val selectedSeats = SelectedSeats(SeatingChart(), 1)
        selectedSeats.tryAddOrDeleteSeat(0, 0)
        val expectedSize = selectedSeats.seats.size

        // when
        selectedSeats.tryAddOrDeleteSeat(row, col)

        // then
        assertThat(selectedSeats.seats.size).isEqualTo(expectedSize)
    }

    @ParameterizedTest
    @CsvSource("0,0,0", "0,2,20000", "2,2,25000", "4,3,22000")
    fun `선택한 좌석의 총 금액을 알 수 있다`(
        row: Int,
        col: Int,
        totalPrice: Int,
    ) {
        // given
        val selectedSeats = SelectedSeats(SeatingChart(), 2)
        selectedSeats.tryAddOrDeleteSeat(0, 0)

        // when
        selectedSeats.tryAddOrDeleteSeat(row, col)

        // then
        assertThat(selectedSeats.totalPrice()).isEqualTo(totalPrice)
    }
}
