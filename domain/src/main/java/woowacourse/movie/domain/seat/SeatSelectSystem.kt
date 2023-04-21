package woowacourse.movie.domain.seat

import woowacourse.movie.domain.theater.TheaterInfo
import woowacourse.movie.domain.ticket.Price

class SeatSelectSystem(
    private val theaterInfo: TheaterInfo,
    private val count: Int,
) {
    private val selectSeats = mutableSetOf<Seat>()
    val seats: List<Seat>
        get() = selectSeats.toList()

    fun select(row: Int, col: Int): SelectResult {
        if (isWrongInput(row, col)) return SelectResult.WrongInput
        if (selectSeats.contains(Seat(row, col))) { // 이미 잡힌 자리인 경우 - 해제
            selectSeats.remove(Seat(row, col))
            return SelectResult.Success.Deselection(theaterInfo.getGradePrice(row) ?: Price())
        }
        // 아직 안 잡힌 자리인 경우 - 선택
        if (isSelectAll()) {
            selectSeats.add(Seat(row, col))
            return SelectResult.Success.Selection(theaterInfo.getGradePrice(row) ?: Price())
        }
        // 이미 자리를 다 고른 상태인 경우
        return SelectResult.MaxSelection
    }

    private fun isWrongInput(row: Int, col: Int): Boolean =
        !theaterInfo.isValidSeat(row, col) || theaterInfo.getGradePrice(row) == null

    private fun isSelectAll(): Boolean = selectSeats.size < count
}
