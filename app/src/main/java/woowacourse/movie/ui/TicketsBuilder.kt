package woowacourse.movie.ui

import woowacourse.movie.ticket.Seat
import woowacourse.movie.ticket.Ticket
import java.io.Serializable
import java.time.LocalDateTime

class TicketsBuilder : Serializable {
    private var movieId: Long? = null
    private lateinit var bookedDateTime: LocalDateTime
    private lateinit var seats: List<Seat>
    private var ticketCount: Int? = null

    fun movieId(movieId: Long) {
        this.movieId = movieId
    }

    fun bookedDateTime(bookedDateTime: LocalDateTime) {
        this.bookedDateTime = bookedDateTime
    }

    fun ticketCount(ticketCount: Int) {
        this.ticketCount = ticketCount
    }

    fun seats(seats: List<Seat>) {
        this.seats = seats
    }

    fun build(): List<Ticket> {
        val id: Long = movieId ?: throw NullPointerException("movieId가 널입니다.")
        return seats.map { Ticket(id, bookedDateTime, it) }
    }
}
