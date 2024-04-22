package woowacourse.movie.domain

import woowacourse.movie.domain.movie.Screen
import woowacourse.movie.domain.seat.Seats

class Ticket(val screen: Screen) {
    var count: Int = DEFAULT_TICKET_COUNT
    val seats: Seats = Seats()

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
