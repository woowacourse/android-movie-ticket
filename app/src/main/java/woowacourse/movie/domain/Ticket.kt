package woowacourse.movie.domain

import woowacourse.movie.domain.seat.Seats
import java.time.LocalDateTime

class Ticket(val screenId: Long) {
    var count: Int = DEFAULT_TICKET_COUNT
    val seats: Seats = Seats()
    lateinit var dateTime : LocalDateTime

    fun addCount() {
        count++
    }

    fun subCount() {
        if (count > 1) count--
    }

    companion object {
        const val DEFAULT_TICKET_COUNT = 1
    }
}
