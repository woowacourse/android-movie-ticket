package woowacourse.movie.presentation.view.reservation.result

import android.widget.TextView
import woowacourse.movie.R
import woowacourse.movie.domain.model.ReservationInfo
import woowacourse.movie.presentation.extension.toDateTimeFormatter

class ReservationResultViews(
    private val activity: ReservationResultActivity,
) {
    private val tvCancelDescription: TextView = activity.findViewById(R.id.tv_cancel_description)
    private val tvMovieTitle: TextView = activity.findViewById(R.id.tv_movie_title)
    private val tvMovieDate: TextView = activity.findViewById(R.id.tv_movie_date)
    private val tvReservationCountInfo: TextView =
        activity.findViewById(R.id.tv_reservation_count_info)
    private val tvTotalPrice: TextView = activity.findViewById(R.id.tv_reservation_total_price)

    fun bindReservationResult(info: ReservationInfo) {
        tvMovieTitle.text = info.title
        activity
            .getString(R.string.reservation_datetime_format)
            .toDateTimeFormatter()
            ?.let { formatter ->
                tvMovieDate.text = info.reservationDateTime.format(formatter)
            }
        tvReservationCountInfo.text =
            activity.getString(R.string.reservation_count_info).format(info.reservationCount.value)
        tvTotalPrice.text =
            activity.getString(R.string.reservation_total_price).format(info.totalPrice())
        tvCancelDescription.text =
            activity.getString(
                R.string.reservation_result_cancel_time_description,
                CANCELLATION_TIME,
            )
    }

    companion object {
        private const val CANCELLATION_TIME = 15
    }
}
