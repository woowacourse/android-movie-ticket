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

    fun setData(
        reservationInfo: ReservationInfoUiModel,
        screen: ScreenUiModel,
        selectedSeats: List<SeatUiModel>,
    ) {
        tvTitle.text = reservationInfo.title
        renderSeatLayout(screen, selectedSeats)
    }

    fun setEventListeners(
        onClickConfirm: () -> Unit,
        onClickSeat: (SeatUiModel) -> Unit,
    ) {
        cachedSeatViews.forEach { (seat, view) -> view.setupSeatClickListener(seat, onClickSeat) }
        btnConfirm.setOnClickListener { onClickConfirm() }
    }

    fun updateTotalPrice(price: Int) {
        tvTotalPrice.text =
            activity.getString(R.string.reservation_select_total_price_format, price)
    }

    fun updateSeatState(seat: SeatUiModel) {
        cachedSeatViews[seat]?.toggleSeatBackgroundColor()
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
    ) {
        var currentRow = -1
        var tableRow: TableRow? = null

        screen.seats.forEach { seat ->
            if (seat.row != currentRow) {
                tableRow?.let { addRowToLayout(it) }
                tableRow = createTableRow()
                currentRow = seat.row
            }
            val seatView = createSeatView(seat, selectedSeats)
            tableRow?.addView(seatView)
        }
        tableRow?.let { addRowToLayout(it) }
    }

    private fun addRowToLayout(row: TableRow) {
        tableLayout.addView(row)
    }

    private fun createTableRow(): TableRow = TableRow(activity)

    private fun createSeatView(
        seat: SeatUiModel,
        selectedSeats: List<SeatUiModel>,
    ): TextView =
        TextView(activity).apply {
            setupSeatText(seat)
            setupSeatStyle(seat, selectedSeats.contains(seat))
            setupSeatLayoutParams()
            cachedSeatViews[seat] = this
        }

    private fun TextView.setupSeatText(seat: SeatUiModel) {
        text = seat.toLabel()
        gravity = Gravity.CENTER
        textSize = 22f
    }

    private fun TextView.setupSeatStyle(
        seat: SeatUiModel,
        isSelected: Boolean,
    ) {
        setTextColor(seat.type.getSeatColor())
        if (isSelected) toggleSeatBackgroundColor()
    }

    private fun TextView.setupSeatLayoutParams() {
        layoutParams = TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 1f)
        post {
            val width = width
            layoutParams.height = width
            requestLayout()
        }
    }

    private fun TextView.setupSeatClickListener(
        seat: SeatUiModel,
        onClickSeat: (SeatUiModel) -> Unit,
    ) {
        tag = seat
        setOnClickListener { onClickSeat(seat) }
    }

    private fun SeatTypeUiModel.getSeatColor(): Int =
        when (this) {
            SeatTypeUiModel.S_CLASS -> activity.getColor(R.color.purple_8e)
            SeatTypeUiModel.A_CLASS -> activity.getColor(R.color.green_19)
            SeatTypeUiModel.B_CLASS -> activity.getColor(R.color.blue_1b)
        }

    private fun TextView.toggleSeatBackgroundColor() {
        val isSelected = this.getTag(id) as? Boolean ?: false
        val newColorRes = if (isSelected) R.color.white else R.color.yellow_fa
        setTag(id, !isSelected)
        setBackgroundResource(newColorRes)
    }
}
