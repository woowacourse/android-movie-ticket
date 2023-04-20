package woowacourse.movie.model.mapper

import com.example.domain.model.Ticket
import com.example.domain.model.Tickets
import woowacourse.movie.model.ReservationSeat

fun ReservationSeat.asDomain(): Tickets = Tickets(
    seats.map {
        Ticket(
            reservationState.movieState.asDomain(),
            reservationState.dateTime,
            it.asDomain()
        )
    }
)
