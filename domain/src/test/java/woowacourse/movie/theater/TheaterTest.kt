package woowacourse.movie.theater

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import woowacourse.movie.ticket.Position
import woowacourse.movie.ticket.Seat
import woowacourse.movie.ticket.SeatRank

class TheaterTest {
    private lateinit var theater: Theater

    @BeforeEach
    fun initTheater() {
        theater = Theater(
            id = 0,
            rowSize = 5,
            columnSize = 4,
            sRankRange = listOf(2..3),
            aRankRange = listOf(4..4),
            bRankRange = listOf(0..1),
        )
    }

    @Test
    fun B등급_위치를_넘기면_B등급_티켓을_반환한다() {
        // given
        val position = Position(0, 0)
        val expected = Seat(position = position, rank = SeatRank.B)

        // when
        val actual: Seat = theater.selectSeat(position)

        // then
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun A등급_위치를_넘기면_A등급_티켓을_반환한다() {
        // given
        val position = Position(4, 3)
        val expected = Seat(position = position, rank = SeatRank.A)

        // when
        val actual: Seat = theater.selectSeat(position)

        // then
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun S등급_위치를_넘기면_S등급_티켓을_반환한다() {
        // given
        val position = Position(2, 0)
        val expected = Seat(position = position, rank = SeatRank.S)

        // when
        val actual: Seat = theater.selectSeat(position)

        // then
        assertThat(actual).isEqualTo(expected)
    }
}
