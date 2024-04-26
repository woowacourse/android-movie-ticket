package woowacourse.movie.feature.complete.ui

import android.content.Context
import woowacourse.movie.R
import woowacourse.movie.model.data.dto.Movie
import woowacourse.movie.model.reservation.Ticket
import woowacourse.movie.model.seat.Seat
import woowacourse.movie.model.seat.SelectedSeats
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class MovieReservationCompleteUiModel(
    val titleMessage: String,
    val screeningDateTimeMessage: String,
    val seatsInfoMessage: String,
    val reservationAmountMessage: String,
) {
    companion object {
        fun of(
            context: Context,
            movie: Movie,
            ticket: Ticket,
        ): MovieReservationCompleteUiModel {
            return MovieReservationCompleteUiModel(
                movie.title,
                screeningDateTimeMessage(ticket.screeningDateTime),
                seatsInfoMessage(context, ticket.selectedSeats),
                reservationAmountMessage(context, ticket),
            )
        }

        private fun screeningDateTimeMessage(screeningTime: LocalDateTime): String {
            return screeningTime.format(DateTimeFormatter.ofPattern("yyyy.M.d HH:mm"))
        }

        private fun seatsInfoMessage(
            context: Context,
            selectedSeats: SelectedSeats,
        ): String {
            val seats = selectedSeats.seats.sortedWith(compareBy({ it.row }, { it.col }))
            val seatsMessage = seats.joinToString(separator = ", ") { it.message() }
            return context.resources.getString(R.string.seats_info)
                .format(selectedSeats.reservationCount.count, seatsMessage)
        }

        private fun Seat.message() = "${(row + 'A'.code - 1).toChar()}$col"

        private fun reservationAmountMessage(
            context: Context,
            ticket: Ticket,
        ): String = context.resources.getString(R.string.reservation_amount).format(ticket.amount().amount)
    }
}
