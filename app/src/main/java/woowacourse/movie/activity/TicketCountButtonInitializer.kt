package woowacourse.movie.activity

import android.widget.Button
import android.widget.Spinner
import android.widget.TextView
import domain.reservation.Reservation
import domain.reservation.TicketCount
import woowacourse.movie.model.ActivityMovieModel
import woowacourse.movie.model.ActivityReservationModel
import woowacourse.movie.model.toActivityModel
import woowacourse.movie.model.toDomainModel
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

class TicketCountButtonInitializer(
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

    fun setOnCompletedButtonClicked(
        movie: ActivityMovieModel,
        screeningDateSpinner: Spinner,
        screeningTimeSpinner: Spinner,
        onCompleted: (reservation: ActivityReservationModel) -> Unit
    ) {
        completeButton.setOnClickListener {
            val ticketCount = ticketCountTextView.text.toString().toInt()
            val reservation: ActivityReservationModel =
                Reservation
                    .from(
                        movie.toDomainModel(),
                        ticketCount,
                        LocalDateTime.of(
                            screeningDateSpinner.selectedItem as LocalDate,
                            screeningTimeSpinner.selectedItem as LocalTime
                        )
                    )
                    .toActivityModel()

            onCompleted(reservation)
        }
    }
}
