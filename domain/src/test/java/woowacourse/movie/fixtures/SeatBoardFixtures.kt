package woowacourse.movie.fixtures

import woowacourse.movie.model.HeadCount
import woowacourse.movie.model.board.Seat
import woowacourse.movie.model.board.SeatBoard
import woowacourse.movie.model.board.SeatState
import woowacourse.movie.model.board.buildSeatBoard

fun seatBoard(headCount: Int = 2, vararg totalSeats: Seat): SeatBoard {
    return buildSeatBoard {
        headCount(HeadCount(headCount))
        totalSeats(totalSeats.toList())
    }
}

fun seatBoard(vararg totalSeats: Seat): SeatBoard {
    return seatBoard(2, *totalSeats)
}

fun SeatBoard.reserveSeats(): Set<Seat> {
    return totalSeats().filter { it.state == SeatState.RESERVED }.toSet()
}

fun SeatBoard.selectedSeats(): Set<Seat> {
    return selectedSeats()
}

fun SeatBoard.emptySeats(): Set<Seat> {
    return totalSeats().filter { it.state == SeatState.EMPTY }.toSet()
}

fun SeatBoard.bannedSeats(): Set<Seat> {
    return totalSeats().filter { it.state == SeatState.BANNED }.toSet()
}

fun SeatBoard.totalPrice(): Long {
    return totalSeats().sumOf { it.price.price }
}