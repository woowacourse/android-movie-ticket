package woowacourse.movie.view.reservation.result

import android.content.Context
import woowacourse.movie.R
import woowacourse.movie.domain.model.ReservationInfo
import woowacourse.movie.domain.model.Seat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class ReservationResultPresenter(
    private val view: ReservationResultContract.View,
    private val context: Context, // Context 인자
) : ReservationResultContract.Presenter {
    override fun loadReservationInfo(reservationInfo: ReservationInfo?) {
        if (reservationInfo == null || reservationInfo.title.isNullOrEmpty()) {
            view.showMessage(context.getString(R.string.invalid_reservation_message))
            return
        }

        val title = reservationInfo.title

        val dateTime = formatReservationDateTime(reservationInfo.reservationDateTime)

        val count = context.getString(R.string.reservation_count_format, reservationInfo.reservationCount.value)

        val seats = formatSelectedSeats(reservationInfo.seats)

        val totalPrice = context.getString(R.string.reservation_total_price).format(reservationInfo.totalPrice())

        view.showReservationResult(title, dateTime, count, seats, totalPrice)
    }

    private fun formatReservationDateTime(dateTime: LocalDateTime): String {
        val formatter = DateTimeFormatter.ofPattern(context.getString(R.string.reservation_datetime_format)) // Context 사용하여 포맷 문자열 가져오기
        return dateTime.format(formatter)
    }

    private fun formatSelectedSeats(seats: List<Seat>): String {
        if (seats.isEmpty()) {
            return context.getString(R.string.no_seats_selected)
        }
        return seats.joinToString(", ") { "${'A' + it.row}${it.column + 1}" } // <-- 여기서 Int row/column을 "A1" 형식으로 포맷
    }
}
