package presenter

import domain.Failure
import domain.Result
import domain.Success
import domain.Ticket
import view.ReservationDetailContract

class ReservationDetailPresenter(private val contract: ReservationDetailContract) {
    val ticket = Ticket()

    fun increaseTicketCount() {
        val result = ticket.increaseCount()
        handleNumberOfTicketsBounds(result, ticket)
    }

    fun decreaseTicketCount() {
        val result = ticket.decreaseCount()
        handleNumberOfTicketsBounds(result, ticket)
    }

    private fun handleNumberOfTicketsBounds(
        result: Result,
        ticket: Ticket,
    ) {
        when (result) {
            is Success -> contract.changeNumberOfTickets(ticket)
            is Failure -> contract.showResultToast()
        }
    }
}
