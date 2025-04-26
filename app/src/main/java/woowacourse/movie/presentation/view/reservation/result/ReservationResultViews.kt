package woowacourse.movie.presentation.view.reservation.result

import android.widget.TextView
import woowacourse.movie.R
import woowacourse.movie.presentation.extension.toDateTimeFormatter
import woowacourse.movie.presentation.model.TicketBundleUiModel
import woowacourse.movie.presentation.util.CustomAlertDialog

class ReservationResultViews(
    private val activity: ReservationResultActivity,
) {
    private val tvCancelDescription: TextView = activity.findViewById(R.id.tv_cancel_description)
    private val tvMovieTitle: TextView = activity.findViewById(R.id.tv_movie_title)
    private val tvMovieDate: TextView = activity.findViewById(R.id.tv_movie_date)
    private val tvReservationCountInfo: TextView =
        activity.findViewById(R.id.tv_reservation_count_info)
    private val tvTotalPrice: TextView = activity.findViewById(R.id.tv_reservation_total_price)

    val dialog: CustomAlertDialog by lazy { CustomAlertDialog(activity) }

    fun bindReservationResult(
        ticketBundle: TicketBundleUiModel,
        cancellationTime: Int,
    ) {
        tvMovieTitle.text = ticketBundle.title
        activity
            .getString(R.string.reservation_datetime_format)
            .toDateTimeFormatter()
            ?.let { formatter ->
                tvMovieDate.text = ticketBundle.dateTime.format(formatter)
            }
        tvReservationCountInfo.text =
            activity.getString(R.string.reservation_count_info, ticketBundle.size, ticketBundle.labels.joinToString())
        tvTotalPrice.text =
            activity.getString(R.string.reservation_total_price).format(ticketBundle.totalPrice)
        tvCancelDescription.text =
            activity.getString(
                R.string.reservation_result_cancel_time_description,
                cancellationTime,
            )
    }
}
