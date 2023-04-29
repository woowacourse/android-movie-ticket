package woowacourse.movie.movieReservation

import android.view.View
import android.widget.Button
import movie.ScreeningDate
import woowacourse.movie.R
import java.time.LocalDate
import java.time.LocalDateTime

class ReservationNavigation(
    view: View,
    startDate: LocalDate,
    endDate: LocalDate,
    onReservationButtonClicked: () -> Unit,
) {
    private val dateSpinner = ReservationDateSpinner(
        view,
        ScreeningDate.getScreeningDates(startDate, endDate),
    ) { timeSpinner.initTimeSpinner(ScreeningDate.getScreeningTimes(it)) }

    private val timeSpinner = ReservationTimeSpinner(view)
    private val ticketQuantityView = ReservationTicketQuantity(view)
    private val submitButton: Button = view.findViewById(R.id.reservation_complete_button)

    val ticketQuantity: Int
        get() = ticketQuantityView.quantity

    val selectedDateTime: LocalDateTime
        get() = LocalDateTime.of(dateSpinner.selectedDate, timeSpinner.selectedTime)

    init {
        timeSpinner.initTimeSpinner(ScreeningDate.getScreeningTimes(dateSpinner.selectedDate))
        submitButton.setOnClickListener { onReservationButtonClicked() }
    }

    fun setTicketQuantity(quantity: Int) {
        ticketQuantityView.quantity = quantity
    }
}
