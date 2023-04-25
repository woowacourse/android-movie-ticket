package woowacourse.movie

import woowacourse.movie.ticket.Seat

class SelectedSeat(private val ticketCount: Int) {
    private val _seats = mutableSetOf<Seat>()
    val seats get() = _seats.toSet()

    val isSeatFull: Boolean
        get() = _seats.size == ticketCount

    fun clickSeat(seat: Seat): SelectResult {
        if (_seats.contains(seat)) {
            return unSelectSeat(seat)
        }
        return selectSeat(seat)
    }

    private fun selectSeat(seat: Seat): SelectResult {
        if (isSeatFull) {
            return SelectResult.Select.Full
        }
        _seats.add(seat)
        return SelectResult.Select.Success
    }

    private fun unSelectSeat(seat: Seat): SelectResult {
        _seats.remove(seat)
        return SelectResult.Unselect
    }
}
