package woowacourse.movie.model.ticket

import woowacourse.movie.model.movie.ScreeningDateTime
import woowacourse.movie.model.result.ChangeTicketCountResult
import woowacourse.movie.model.result.Failure
import woowacourse.movie.model.result.Success
import woowacourse.movie.model.seats.Seats
import java.io.Serializable

class Ticket(
    count: Int = DEFAULT_TICKET_COUNT,
    screeningDateTime: ScreeningDateTime = ScreeningDateTime("", ""),
) : Serializable {
    var count: Int = count
        private set
    var screeningDateTime = screeningDateTime
        private set

    private fun restoreCount(recordOfCount: Int) {
        count = recordOfCount
    }

    fun restoreTicket(count: Int): Ticket {
        restoreCount(count)
        return this
    }

    fun increaseCount(): ChangeTicketCountResult {
        if (count >= MAX_TICKET_COUNT) return Failure
        count++
        return Success
    }

    fun decreaseCount(): ChangeTicketCountResult {
        if (count <= MIN_TICKET_COUNT) return Failure
        count--
        return Success
    }

    fun calculateAmount(seats: Seats): Int {
        return seats.seats.sumOf { it.grade.price }
    }

    companion object {
        private const val DEFAULT_TICKET_COUNT = 1
        private const val MAX_TICKET_COUNT = 20
        private const val MIN_TICKET_COUNT = 1
    }
}
