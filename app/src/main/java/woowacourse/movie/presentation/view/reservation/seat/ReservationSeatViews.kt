package woowacourse.movie.presentation.view.reservation.seat

import android.view.Gravity
import android.widget.Button
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import androidx.core.view.children
import woowacourse.movie.R
import woowacourse.movie.presentation.model.ReservationInfoUiModel
import woowacourse.movie.presentation.model.ScreenUiModel
import woowacourse.movie.presentation.model.SeatTypeUiModel
import woowacourse.movie.presentation.model.SeatUiModel
import woowacourse.movie.presentation.util.CustomAlertDialog

class ReservationSeatViews(
    private val activity: ReservationSeatActivity,
) {
    private val tvTitle: TextView by lazy { activity.findViewById(R.id.tv_seats_movie_title) }
    private val tvTotalPrice: TextView by lazy { activity.findViewById(R.id.tv_seats_total_price) }
    private val tableLayout: TableLayout by lazy { activity.findViewById(R.id.tb_seats) }
    private val btnConfirm: Button by lazy { activity.findViewById(R.id.btn_confirm) }

    val dialog: CustomAlertDialog by lazy { CustomAlertDialog(activity) }

    fun bind(
        reservationInfo: ReservationInfoUiModel,
        screen: ScreenUiModel,
        onClickConfirm: () -> Unit,
        onClickSeat: (SeatUiModel) -> Unit,
    ) {
        tvTitle.text = reservationInfo.title
        renderSeatLayout(screen, onClickSeat)
        btnConfirm.setOnClickListener { onClickConfirm() }
    }

    fun updateTotalPrice(price: Int) {
        tvTotalPrice.text = activity.getString(R.string.reservation_select_total_price_format, price)
    }

    fun updateSeatsView(seats: List<SeatUiModel>) {
        val tableRows = tableLayout.children.map { it as TableRow }
        tableRows.forEach { tableRow ->
            val seatViews = tableRow.children.map { it as TextView }
            seatViews.forEach {
                if (seats.contains(it.tag)) {
                    it.setBackgroundResource(R.color.yellow_fa)
                } else {
                    it.setBackgroundResource(R.color.white)
                }
            }
        }
    }

    fun updateConfirmButton(canPublish: Boolean) {
        val color = if (canPublish) R.color.purple_62 else R.color.gray_b7
        btnConfirm.apply {
            isEnabled = canPublish
            setBackgroundResource(color)
        }
    }

    private fun renderSeatLayout(
        screen: ScreenUiModel,
        onClickSeat: (SeatUiModel) -> Unit,
    ) {
        var currentRow = -1
        var tableRow: TableRow? = null

        screen.seats.forEach { seat ->
            if (seat.row != currentRow) {
                tableRow?.let { tableLayout.addView(it) }
                tableRow = createTableRow()
                currentRow = seat.row
            }

            val seatView = createSeatView(seat, onClickSeat)
            tableRow?.addView(seatView)
        }

        tableRow?.let { tableLayout.addView(it) }
    }

    private fun createTableRow(): TableRow = TableRow(activity)

    private fun createSeatView(
        seat: SeatUiModel,
        onClickSeat: (SeatUiModel) -> Unit,
    ): TextView =
        TextView(activity).apply {
            text = "${'A' + seat.row}${seat.col}"
            gravity = Gravity.CENTER
            textSize = 22f
            setTextColor(seat.type.getSeatColor())
            layoutParams = TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 1f)
            tag = seat

            post {
                val width = width
                layoutParams.height = width
                requestLayout()
            }

            setOnClickListener {
                onClickSeat(seat)
            }
        }

    private fun SeatTypeUiModel.getSeatColor(): Int =
        when (this) {
            SeatTypeUiModel.S_CLASS -> activity.getColor(R.color.purple_8e)
            SeatTypeUiModel.A_CLASS -> activity.getColor(R.color.green_19)
            SeatTypeUiModel.B_CLASS -> activity.getColor(R.color.blue_1b)
        }
}
