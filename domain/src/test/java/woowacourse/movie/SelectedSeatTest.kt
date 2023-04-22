package woowacourse.movie

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import woowacourse.movie.ticket.Position
import woowacourse.movie.ticket.Seat
import woowacourse.movie.ticket.SeatRank

class SelectedSeatTest {
    private lateinit var selectedSeat: SelectedSeat

    @BeforeEach
    fun initSelectedSeat() {
        selectedSeat = SelectedSeat(3)
    }

    @Test
    fun `빈 좌석을 클릭하면 좌석이 추가된다`() {
        // given
        val seat = Seat(SeatRank.B, Position(0, 0))
        val expected: Int = selectedSeat.seats.size + 1

        // when
        selectedSeat.clickSeat(seat)
        val actual = selectedSeat.seats.size

        // then
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `이미 클릭한 좌석을 클릭하면 좌석 선택이 해제된다`() {
        // given
        val seat = Seat(SeatRank.B, Position(0, 0))
        selectedSeat.clickSeat(seat)
        val expected: Int = selectedSeat.seats.size - 1

        // when
        selectedSeat.clickSeat(seat)
        val actual = selectedSeat.seats.size

        // then
        assertThat(actual).isEqualTo(expected)
    }
}
