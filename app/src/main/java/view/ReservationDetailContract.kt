package view

import domain.Ticket

interface ReservationDetailContract {
    fun showResultToast()

    fun changeNumberOfTickets(ticket: Ticket)
}
