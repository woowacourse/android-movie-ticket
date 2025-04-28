package woowacourse.movie.presentation.view.reservation.seat

import android.view.Gravity
import android.widget.Button
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
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

    private val cachedSeatViews: MutableMap<SeatUiModel, TextView> = mutableMapOf()

    val dialog: CustomAlertDialog by lazy { CustomAlertDialog(activity) }

    fun bind(
        reservationInfo: ReservationInfoUiModel,
        screen: ScreenUiModel,
        selectedSeats: List<SeatUiModel>,
        onClickConfirm: () -> Unit,
        onClickSeat: (SeatUiModel) -> Unit,
    ) {
        tvTitle.text = reservationInfo.title
        renderSeatLayout(screen, selectedSeats, onClickSeat)
        btnConfirm.setOnClickListener { onClickConfirm() }
    }

    fun updateTotalPrice(price: Int) {
        tvTotalPrice.text =
            activity.getString(R.string.reservation_select_total_price_format, price)
    }

    fun updateSeatState(seat: SeatUiModel) {
        cachedSeatViews[seat]?.setSeatBackgroundColor()
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
        selectedSeats: List<SeatUiModel>,
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
            val isSelected = selectedSeats.contains(seat)
            val seatView = createSeatView(seat, isSelected, onClickSeat)
            cachedSeatViews[seat] = seatView
            tableRow?.addView(seatView)
        }

        tableRow?.let { tableLayout.addView(it) }
    }

    private fun createTableRow(): TableRow = TableRow(activity)

    private fun createSeatView(
        seat: SeatUiModel,
        isSelected: Boolean,
        onClickSeat: (SeatUiModel) -> Unit,
    ): TextView =
        TextView(activity).apply {
            text = "${'A' + seat.row}${seat.col}"
            gravity = Gravity.CENTER
            textSize = 22f
            setTextColor(seat.type.getSeatColor())
            if (isSelected) this.setSeatBackgroundColor()
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

    private fun TextView.setSeatBackgroundColor() {
        val currentColorTag = this.getTag(id) as? Boolean ?: false
        val newColor =
            if (!currentColorTag) {
                setTag(id, true)
                R.color.yellow_fa
            } else {
                setTag(id, false)
                R.color.white
            }

        this.setBackgroundResource(newColor)
    }
}
