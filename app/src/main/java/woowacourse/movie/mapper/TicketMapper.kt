package woowacourse.movie.mapper

import com.example.domain.model.model.Ticket
import com.example.domain.model.price.Price
import woowacourse.movie.model.TicketModel

fun TicketModel.toTicket() =
    Ticket(reservationInfoModel.toReservationInfo(), Price(price), seats.map { it.toSeat() })

fun Ticket.toTicketModel() = TicketModel(reservationInfo.toReservationInfoModel(), price.price, seats.map { it.toSeatModel() })
