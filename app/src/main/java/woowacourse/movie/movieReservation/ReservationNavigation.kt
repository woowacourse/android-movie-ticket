package woowacourse.movie.movieReservation

import android.view.View
import movie.TicketQuantity
import java.time.LocalDate
import java.time.LocalDateTime

class ReservationNavigation(
    view: View,
    startDate: LocalDate,
    endDate: LocalDate,
    onReservationButtonClicked: () -> Unit,
) {
    private val dateSpinner = ReservationDateSpinner(view, startDate, endDate) { timeSpinner.initTimeSpinner(it) }
    private val timeSpinner = ReservationTimeSpinner(view)
    private val ticketQuantityView = ReservationTicketQuantity(view)

    val ticketQuantity: TicketQuantity
        get() = ticketQuantityView.quantity

    val selectedDateTime: LocalDateTime
        get() = LocalDateTime.of(dateSpinner.selectedDate, timeSpinner.selectedTime)

    init {
        timeSpinner.initTimeSpinner(dateSpinner.selectedDate)
        ReservationSubmit(view) { onReservationButtonClicked() }
        ticketQuantityView.initTicketQuantity(ticketQuantity)
    }

    fun setTicketQuantity(ticketQuantity: TicketQuantity) {
        ticketQuantityView.initTicketQuantity(ticketQuantity)
    }
}
