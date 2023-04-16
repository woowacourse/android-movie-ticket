package woowacourse.movie.movieReservation

import android.os.Bundle
import android.view.View
import entity.Screening
import movie.TicketCount
import java.time.LocalDateTime

class ReservationNavigation(
    view: View,
    screening: Screening,
    onReservationButtonClicked: () -> Unit,
) {
    private val timeSpinner: TimeSpinner = TimeSpinner(view)
    private val dateSpinner: DateSpinner = DateSpinner(view) { timeSpinner.initTimeSpinner(it) }
    private val ticketCountView: TicketCountView = TicketCountView(view)

    val ticketCount: TicketCount get() = TicketCount(ticketCountView.getTicketCount())
    val selectedDateTime: LocalDateTime get() = LocalDateTime.of(dateSpinner.getSelectedDate(), timeSpinner.getSelectedTime())

    init {
        dateSpinner.initDateSpinner(screening)
        ReservationSubmit(view) { onReservationButtonClicked() }
    }

    fun load(savedInstanceState: Bundle) {
        ticketCountView.load(savedInstanceState)
        dateSpinner.load(savedInstanceState)
        timeSpinner.load(savedInstanceState)
    }

    fun save(outState: Bundle) {
        ticketCountView.save(outState)
        timeSpinner.save(outState)
        dateSpinner.save(outState)
    }
}
