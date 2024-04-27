package woowacourse.movie.presentation.reservation.seat

import woowacourse.movie.model.HeadCount
import woowacourse.movie.model.Price
import woowacourse.movie.model.board.Position
import woowacourse.movie.model.board.Seat
import woowacourse.movie.model.board.SeatBoard
import woowacourse.movie.model.board.SeatGrade
import woowacourse.movie.model.board.SeatState
import woowacourse.movie.model.board.buildSeatBoard
import woowacourse.movie.presentation.reservation.seat.model.SeatBoardUiModel
import woowacourse.movie.presentation.reservation.seat.model.SeatGradeUiModel
import woowacourse.movie.presentation.reservation.seat.model.SeatStateUiModel
import woowacourse.movie.presentation.reservation.seat.model.SeatUiModel

// domain -> ui

fun SeatBoard.toUiModel(): SeatBoardUiModel {
    return SeatBoardUiModel(
        columnCount = boardSize.width,
        rowCount = boardSize.height,
        headCount = headCount.count,
        seats = toSeatsUiModel()
    )
}

fun SeatBoard.toSeatsUiModel(): List<SeatUiModel> {
    return totalSeats().map {
        it.toUiModel()
    }
}

fun Seat.toUiModel(): SeatUiModel {
    return SeatUiModel(
        x = position.x,
        y = position.y,
        state = state.toUiModel(),
        seatGradeUiModel = grade.toUiModel(),
        price = price.price
    )
}

private fun SeatState.toUiModel(): SeatStateUiModel {
    return when (this) {
        SeatState.EMPTY -> SeatStateUiModel.EMPTY
        SeatState.SELECTED -> SeatStateUiModel.SELECT
        SeatState.BANNED -> SeatStateUiModel.BANNED
        SeatState.RESERVED -> SeatStateUiModel.RESERVED
    }
}

private fun SeatGrade.toUiModel(): SeatGradeUiModel {
    return when (this) {
        SeatGrade.B -> SeatGradeUiModel.B
        SeatGrade.A -> SeatGradeUiModel.A
        SeatGrade.S -> SeatGradeUiModel.S
    }
}

// ui -> domain

fun SeatBoardUiModel.toDomain(): SeatBoard {
    return buildSeatBoard {
        size(columnCount, rowCount)
        headCount(HeadCount(headCount))
        totalSeats(seats.map { it.toDomain() })
    }
}

private fun SeatUiModel.toDomain(): Seat {
    return Seat(
        position = Position(x, y),
        state = state.toDomain(),
        grade = seatGradeUiModel.toDomain(),
        price = Price(price)
    )
}

private fun SeatStateUiModel.toDomain(): SeatState {
    return when (this) {
        SeatStateUiModel.EMPTY -> SeatState.EMPTY
        SeatStateUiModel.SELECT -> SeatState.SELECTED
        SeatStateUiModel.BANNED -> SeatState.BANNED
        SeatStateUiModel.RESERVED -> SeatState.RESERVED
    }
}

private fun SeatGradeUiModel.toDomain(): SeatGrade {
    return when (this) {
        SeatGradeUiModel.B -> SeatGrade.B
        SeatGradeUiModel.A -> SeatGrade.A
        SeatGradeUiModel.S -> SeatGrade.S
    }
}