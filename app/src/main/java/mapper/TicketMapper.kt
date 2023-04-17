package mapper

import data.Ticket
import model.TicketModel

fun TicketModel.toTicket() = Ticket(
    title = title,
    reserveTime = reserveTime,
    price = price,
    peopleNumber = peopleNumber,
)

fun Ticket.toTicketModel() = TicketModel(
    title = title,
    reserveTime = reserveTime,
    price = price,
    peopleNumber = peopleNumber,
)
