package woowacourse.movie.presentation.reservation.seat

import woowacourse.movie.model.HeadCount
import woowacourse.movie.model.board.Position
import woowacourse.movie.model.board.SeatBoard
import woowacourse.movie.model.board.buildSeatBoard
import woowacourse.movie.presentation.reservation.booking.model.SeatSelectionNavArgs
import woowacourse.movie.presentation.reservation.seat.model.SeatBoardUiModel
import woowacourse.movie.presentation.reservation.seat.model.SeatSelectionUiState
import java.time.LocalDateTime

fun stubSeatSelectionNavArgs(): SeatSelectionNavArgs =
    SeatSelectionNavArgs(
        screenMovieId = 1,
        movieTitle = "해리 포터와 마법사의 돌",
        selectedDateTime = LocalDateTime.of(2024, 4, 30, 12, 30, 12),
        headCount = 2,
    )

fun stubSeatSelectionUiState(): SeatSelectionUiState =
    SeatSelectionUiState(
        seatBoard = stubSeatBoardUiModel(),
        navArgs = stubSeatSelectionNavArgs(),
    )

fun stubSeatBoardUiModel(): SeatBoardUiModel =
    SeatBoardUiModel(
        columnCount = 5,
        rowCount = 5,
        headCount = 2,
        seats = emptyList(),
    )

fun stubSeatBoard(): SeatBoard =
    buildSeatBoard {
        size(5, 5)
        headCount(HeadCount(2))
        bannedPositions(setOf(banPosition()))
    }

fun banPosition(): Position = Position(1, 1)
