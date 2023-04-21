package woowacourse.movie.activity.seats

import android.content.Context
import android.content.Intent
import android.view.Gravity
import android.widget.TableRow
import android.widget.TextView
import discount.Discount
import discount.EarlyNightDiscount
import discount.MovieDayDiscount
import payment.PaymentAmount
import seat.Seat
import seat.SeatType
import woowacourse.movie.R
import woowacourse.movie.databinding.ActivitySeatSelectionBinding
import java.time.LocalDateTime

class SeatsView(
    private val binding: ActivitySeatSelectionBinding,
    private val intent: Intent
) {

    private val context: Context = binding.root.context
    private val seats: List<List<TextView>> by lazy { createSeatViews() }

    fun set() {
        setSeatSelectEvent()
    }

    fun getSelectedCount(): Int {
        var count = 0
        seats.forEach { count += it.count { seat -> seat.isSelected } }
        return count
    }

    private fun getSelectedSeats(): List<Seat> {
        val selectedSeat: MutableList<Seat> = mutableListOf()

        seats.forEachIndexed { rowIndex, it ->
            it.forEachIndexed() { columnIndex, seat ->
                if (seat.isSelected) {
                    val row: Char = rowIndex.toChar() + Seat.MIN_ROW.toInt()
                    val column: Int = columnIndex + Seat.MIN_COLUMN
                    selectedSeat.add(Seat.from(row, column))
                }
            }
        }

        return selectedSeat
    }

    private fun createSeatViews(): List<List<TextView>> {
        val seats: MutableList<MutableList<TextView>> = mutableListOf()

        for (row in Seat.MIN_ROW..Seat.MAX_ROW) {
            val tableRow = TableRow(context)
            tableRow.layoutParams = TableRow.LayoutParams(
                TableRow.LayoutParams.MATCH_PARENT, 180, 1f
            )
            seats.add(mutableListOf())

            for (col in Seat.MIN_COLUMN..Seat.MAX_COLUMN) {
                val textView = TextView(context)
                textView.layoutParams = TableRow.LayoutParams(
                    TableRow.LayoutParams.MATCH_PARENT, 180, 1f
                )

                textView.text = context.getString(R.string.seat_name_form).format(row, col)
                textView.textSize = context.resources.getDimension(R.dimen.text_very_small)
                textView.setBackgroundColor(context.getColor(R.color.not_selected_seat_color))
                getSeatColorID(Seat.getSeatType(row)).let { if (it != null) textView.setTextColor(it) }
                textView.gravity = Gravity.CENTER

                seats[row - Seat.MIN_ROW].add(textView)
                tableRow.addView(textView)
            }

            binding.seatTableLayout.addView(tableRow)
        }

        return seats
    }

    private fun getSeatColorID(seatType: SeatType?): Int? = when (seatType) {
        SeatType.S -> context.getColor(R.color.seat_s)
        SeatType.A -> context.getColor(R.color.seat_a)
        SeatType.B -> context.getColor(R.color.seat_b)
        else -> null
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

        val selectedSeatCount: Int = getSelectedCount()
        val ticketCount: Int = intent.getIntExtra("ticket_count", 1)
        when {
            ticketCount <= selectedSeatCount -> {
                seats.forEachIndexed { index, it ->
                    it.forEach { seat ->
                        if (!seat.isSelected) seat.isClickable = false
                        binding.reservationCompleteTextView.isClickable = true
                        binding.reservationCompleteTextView.setBackgroundColor(context.getColor(R.color.clickable_button_color))
                        updatePaymentAmount()
                    }
                }
            }
            else -> {
                seats.forEach {
                    it.forEach { seat ->
                        if (!seat.isSelected) seat.isClickable = true
                        binding.reservationCompleteTextView.isClickable = false
                        binding.reservationCompleteTextView.setBackgroundColor(context.getColor(R.color.not_clickable_button_color))
                    }
                }
            }
        }
    }

    private fun updatePaymentAmount() {
        val screeningDateTime: LocalDateTime =
            intent.getSerializableExtra("screening_date_time") as LocalDateTime
        val discount: Discount = Discount(MovieDayDiscount(), EarlyNightDiscount())

        val basePaymentAmount: PaymentAmount = PaymentAmount.from(getSelectedSeats())
        binding.paymentAmountTextView.text =
            discount.getPaymentAmountResult(basePaymentAmount, screeningDateTime).toString()
    }
}
