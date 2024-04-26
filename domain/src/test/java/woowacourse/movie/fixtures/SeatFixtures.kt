package woowacourse.movie.fixtures

import woowacourse.movie.model.Price
import woowacourse.movie.model.board.Position
import woowacourse.movie.model.board.Seat
import woowacourse.movie.model.board.SeatGrade
import woowacourse.movie.model.board.SeatState

private const val DEFAULT_PRICE = 10000L
private val DEFAULT_GRADE = SeatGrade.B
private val DEFAULT_STATE = SeatState.EMPTY

fun seat(
    x: Int, y: Int,
    price: Long = DEFAULT_PRICE,
    grade: SeatGrade = DEFAULT_GRADE,
    state: SeatState = DEFAULT_STATE
) = Seat(Position(x, y), Price(price), grade, state)