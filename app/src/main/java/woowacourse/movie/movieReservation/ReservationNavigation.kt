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
    private val timeSpinner = ReservationTimeSpinner(view)
    private val dateSpinner = ReservationDateSpinner(view) { timeSpinner.initTimeSpinner(it) }
    private val ticketQuantityView = ReservationTicketQuantity(view)

    val ticketQuantity: TicketQuantity
        get() = ticketQuantityView.quantity

    val selectedDateTime: LocalDateTime
        get() = LocalDateTime.of(dateSpinner.selectedDate, timeSpinner.selectedTime)

    init {
        dateSpinner.initDateSpinner(movieListItem)
        timeSpinner.initTimeSpinner(dateSpinner.selectedDate)
        ReservationSubmit(view) { onReservationButtonClicked() }
    }

    fun load(savedInstanceState: Bundle?) {
        val ticketQuantity = TicketQuantity(savedInstanceState?.getInt(KEY_COUNT) ?: 1)
        ticketQuantityView.initTicketQuantity(ticketQuantity)
    }

    fun save(outState: Bundle) {
        ticketQuantityView.quantity.let { outState.putInt(KEY_COUNT, it.toInt()) }
    }

    companion object {
        private const val KEY_COUNT = "count"
    }
}
