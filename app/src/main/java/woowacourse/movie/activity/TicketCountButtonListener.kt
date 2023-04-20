package woowacourse.movie.activity

import android.widget.Button
import android.widget.Spinner
import android.widget.TextView
import domain.reservation.TicketCount
import woowacourse.movie.model.MovieInfo
import woowacourse.movie.model.SeatReservationInfo
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

class TicketCountButtonListener(
    private val minusButton: Button,
    private val plusButton: Button,
    private val completeButton: Button,
    private val ticketCountTextView: TextView
) {

    fun setOnMinusButtonClicked(alertError: () -> Unit) {
        minusButton.setOnClickListener {
            runCatching {
                val ticketCount = TicketCount(ticketCountTextView.text.toString().toInt() - 1)
                ticketCountTextView.text = ticketCount.value.toString()
            }.onFailure {
                alertError()
            }
        }
    }

    fun setOnPlusButtonClicked() {
        plusButton.setOnClickListener {
            val ticketCount = TicketCount(ticketCountTextView.text.toString().toInt() + 1)
            ticketCountTextView.text = ticketCount.value.toString()
        }
    }

    fun setOnSeatSelectionButtonClicked(
        movie: MovieInfo,
        screeningDateSpinner: Spinner,
        screeningTimeSpinner: Spinner,
        onCompleted: (seatReservation: SeatReservationInfo) -> Unit
    ) {
        completeButton.setOnClickListener {
            val ticketCount = ticketCountTextView.text.toString().toInt()
            val reservation = SeatReservationInfo(
                movie.movieName,
                LocalDateTime.of(
                    screeningDateSpinner.selectedItem as LocalDate,
                    screeningTimeSpinner.selectedItem as LocalTime
                ),
                ticketCount
            )

            onCompleted(reservation)
        }
    }
}
