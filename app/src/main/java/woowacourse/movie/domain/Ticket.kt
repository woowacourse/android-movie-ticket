package woowacourse.movie.domain

import woowacourse.movie.domain.seat.Seats
import java.io.Serializable
import java.time.LocalDate

class Ticket(val screenId: Long) : Serializable {
    var count: Int = DEFAULT_TICKET_COUNT
    val seats: Seats = Seats()
    lateinit var date: LocalDate
    lateinit var time: String

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
