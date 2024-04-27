package woowacourse.movie.fixtures

import woowacourse.movie.model.board.Position
import woowacourse.movie.model.board.SeatBoardBuilder

fun SeatBoardBuilder.bannedPositions(vararg positions: Position) {
    bannedPositions(positions.toSet())
}

fun SeatBoardBuilder.reservedSeatPositions(vararg positions: Position) {
    reservedSeatPositions(positions.toSet())
}
