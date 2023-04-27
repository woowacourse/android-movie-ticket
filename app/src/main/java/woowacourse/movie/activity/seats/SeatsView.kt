package woowacourse.movie.activity.seats

import android.content.Context
import android.view.Gravity
import android.widget.TableRow
import android.widget.TextView
import woowacourse.movie.R
import woowacourse.movie.databinding.ActivitySeatSelectionBinding
import woowacourse.movie.domain.payment.PaymentAmount
import woowacourse.movie.domain.seat.Column
import woowacourse.movie.domain.seat.Row
import woowacourse.movie.domain.seat.Seat
import woowacourse.movie.domain.seat.SeatType
import java.text.DecimalFormat
import java.time.LocalDateTime

class SeatsView(
    private val binding: ActivitySeatSelectionBinding,
    private val ticketCount: Int,
    private val screeningDateTime: LocalDateTime
) {

    private val context: Context = binding.root.context
    private lateinit var seats: List<List<TextView>>

    fun set(rowSize: Int, columnSize: Int) {
        seats = createSeatViews(rowSize, columnSize)
        setSeatSelectEvent()
    }

    fun getSelectedCount(): Int {
        var count = 0
        seats.forEach { count += it.count { seat -> seat.isSelected } }
        return count
    }

    fun getSelectedSeats(): List<Seat> {
        return seats.flatMapIndexed { rowIndex, it ->
            List(it.filter { it.isSelected }.size) { columnIndex ->
                val row: Row = Row.of(rowIndex)
                val column: Column = Column.of(columnIndex)
                Seat.from(row.value, column.value)
            }
        }
    }

    private fun createSeatViews(rowSize: Int, columnSize: Int): List<List<TextView>> {
        val seats: MutableList<MutableList<TextView>> = mutableListOf()

        for (row in Row.MINIMUM..Row.MINIMUM + rowSize) {
            val tableRow = TableRow(context)
            tableRow.layoutParams = TableRow.LayoutParams(
                TableRow.LayoutParams.MATCH_PARENT, 180, 1f
            )
            seats.add(mutableListOf())

            for (col in Column.MINIMUM..Column.MINIMUM + columnSize) {
                val textView: TextView = createSeatView(row, col)
                seats[Row.toNumber(row)].add(textView)
                tableRow.addView(textView)
            }

            binding.seatTableLayout.addView(tableRow)
        }

        return seats
    }

    private fun createSeatView(row: Char, col: Int): TextView {
        val textView = TextView(context)
        textView.layoutParams = TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, 180, 1f)

        textView.text = context.getString(R.string.seat_name_form).format(row, col)
        textView.textSize = context.resources.getDimension(R.dimen.text_very_small)
        textView.setSeatColor(row)
        textView.gravity = Gravity.CENTER

        return textView
    }

    private fun setSeatSelectEvent() {
        seats.forEach {
            it.forEach { seat ->
                seat.setOnClickListener { seatClickEvent(seat) }
            }
        }
    }

    private fun seatClickEvent(clickedSeat: TextView) {
        if (!clickedSeat.isClickable) return

        when (clickedSeat.isSelected) {
            true -> {
                clickedSeat.setBackgroundColor(context.getColor(R.color.not_selected_seat_color))
                clickedSeat.isSelected = false
            }
            false -> {
                clickedSeat.setBackgroundColor(context.getColor(R.color.selected_seat_color))
                clickedSeat.isSelected = true
            }
        }

        setReservationCompleteTextView()
        updatePaymentAmount()
    }

    private fun setReservationCompleteTextView() {
        val selectedSeatCount: Int = getSelectedCount()

        if (ticketCount <= selectedSeatCount) {
            seats.forEachIndexed { index, it ->
                it.forEach { seat ->
                    if (!seat.isSelected) seat.isClickable = false
                    binding.reservationCompleteTextView.isClickable = true
                    binding.reservationCompleteTextView.setBackgroundColor(context.getColor(R.color.clickable_button_color))
                }
            }
        } else {
            seats.forEach {
                it.forEach { seat ->
                    if (!seat.isSelected) seat.isClickable = true
                    binding.reservationCompleteTextView.isClickable = false
                    binding.reservationCompleteTextView.setBackgroundColor(context.getColor(R.color.not_clickable_button_color))
                }
            }
        }
    }

    private fun updatePaymentAmount() {
        val paymentAmount: PaymentAmount =
            PaymentAmount.applyDiscount(getSelectedSeats(), screeningDateTime)

        binding.paymentAmountTextView.text =
            context.getString(R.string.payment_amount_form)
                .format(DecimalFormat("#,###").format(paymentAmount.value))
    }

    private fun TextView.setSeatColor(row: Char) {
        this.setBackgroundColor(context.getColor(R.color.not_selected_seat_color))
        this.setTextColor(getSeatColorID(Seat.getSeatType(row)))
    }

    private fun getSeatColorID(seatType: SeatType): Int = when (seatType) {
        SeatType.S -> context.getColor(R.color.seat_s)
        SeatType.A -> context.getColor(R.color.seat_a)
        SeatType.B -> context.getColor(R.color.seat_b)
        else -> throw IllegalArgumentException()
    }
}
