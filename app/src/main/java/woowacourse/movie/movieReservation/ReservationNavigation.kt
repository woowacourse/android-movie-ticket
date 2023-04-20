package woowacourse.movie.movieReservation

import android.os.Bundle
import android.view.View
import model.MovieListItem
import movie.TicketQuantity
import java.time.LocalDateTime

class ReservationNavigation(
    view: View,
    movieListItem: MovieListItem,
    onReservationButtonClicked: () -> Unit,
) {
    private val timeSpinner: ReservationTimeSpinner = ReservationTimeSpinner(view)
    private val dateSpinner: ReservationDateSpinner = ReservationDateSpinner(view) { timeSpinner.initTimeSpinner(it) }
    private val ticketQuantityView: ReservationTicketQuantity = ReservationTicketQuantity(view)

    val ticketQuantity: TicketQuantity
        get() = ticketQuantityView.quantity

    val selectedDateTime: LocalDateTime
        get() = LocalDateTime.of(dateSpinner.selectedDate, timeSpinner.selectedTime)

    init {
        dateSpinner.initDateSpinner(movieListItem)
        ReservationSubmit(view) { onReservationButtonClicked() }
    }

    fun load(savedInstanceState: Bundle?) {
        savedInstanceState?.let {
            ticketQuantityView.load(savedInstanceState)
            dateSpinner.load(savedInstanceState)
            timeSpinner.load(savedInstanceState)
        }
    }

    fun save(outState: Bundle) {
        ticketQuantityView.save(outState)
        timeSpinner.save(outState)
        dateSpinner.save(outState)
    }
}
