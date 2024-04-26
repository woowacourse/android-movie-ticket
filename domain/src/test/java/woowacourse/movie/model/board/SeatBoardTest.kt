package woowacourse.movie.model.board

import io.kotest.matchers.booleans.shouldBeTrue
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import woowacourse.movie.fixtures.seat
import woowacourse.movie.fixtures.seatBoard

class SeatBoardTest {
    @Test
    fun `선택된 좌석을 다시 선택 하면, 좌석이 비워진다`() {
        // given
        val position = Position(1, 2)
        val seatBoard = seatBoard(
            seat(position, state = SeatState.SELECTED)
        )
        val expect = seat(position, state = SeatState.EMPTY)
        // when
        val result = seatBoard.select(position)
        // then
        result.onSuccess { seatBoard, newSeat ->
            newSeat shouldBe expect
        }
    }

    @Test
    fun `빈 좌석을 선택 하면, 선택된 좌석이 된다`() {
        // given
        val position = Position(1, 2)
        val seatBoard = seatBoard(
            seat(position, state = SeatState.EMPTY)
        )
        val expect = seat(position, state = SeatState.SELECTED)
        // when
        val result = seatBoard.select(position)
        // then
        result.onSuccess { seatBoard, newSeat ->
            newSeat shouldBe expect
        }
    }

    @Test
    fun `금지된 좌석을 선택하면, 실패한다`() {
        // given
        val position = Position(1, 2)
        val seatBoard = seatBoard(
            seat(position, state = SeatState.BANNED)
        )
        // when
        val result = seatBoard.select(position)
        // then
        result.isFailure().shouldBeTrue()
    }

    @Test
    fun `예약된 좌석을 선택하면, 실패한다`() {
        // given
        val position = Position(1, 2)
        val seatBoard = seatBoard(
            seat(position, state = SeatState.RESERVED)
        )
        // when
        val result = seatBoard.select(position)
        // then
        result.isFailure().shouldBeTrue()
    }

    @Test
    fun `인원수가 2이고, 선택된 좌석이 2이면 좌석 선택이 완료 된다`() {
        // given
        val seatBoard = seatBoard(
            headCount = 2,
            seat(1, 1, state = SeatState.SELECTED),
            seat(1, 2, state = SeatState.SELECTED),
        )
        // when & then
        seatBoard.isCompletedSelection.shouldBeTrue()
    }

    @Test
    fun `선택이 완료된 후, 빈 좌석을 선택하면 실패한다`() {
        // given
        val seatBoard = seatBoard(
            headCount = 2,
            seat(1, 1, state = SeatState.SELECTED),
            seat(1, 2, state = SeatState.SELECTED),
            seat(1, 3, state = SeatState.EMPTY),
        )
        // when
        val result = seatBoard.select(Position(1, 3))
        // then
        result.isFailure().shouldBeTrue()
    }
}