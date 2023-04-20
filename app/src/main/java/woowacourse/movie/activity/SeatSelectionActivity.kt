package woowacourse.movie.activity

import android.os.Bundle
import android.view.Gravity
import android.widget.TableRow
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import seat.Seat
import seat.Seat.Companion.MAX_COLUMN
import seat.Seat.Companion.MAX_ROW
import seat.Seat.Companion.MIN_COLUMN
import seat.Seat.Companion.MIN_ROW
import seat.SeatType
import woowacourse.movie.R
import woowacourse.movie.databinding.ActivitySeatSelectionBinding

class SeatSelectionActivity : AppCompatActivity() {

    private val binding by lazy { ActivitySeatSelectionBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val seats: List<List<TextView>> = setSeatsViews()
        setSeatSelectEvent(seats)
    }

    private fun setSeatsViews(): List<List<TextView>> {
        val seats: MutableList<MutableList<TextView>> = mutableListOf()

        for (row in MIN_ROW..MAX_ROW) {
            val tableRow = TableRow(this)
            tableRow.layoutParams = TableRow.LayoutParams(
                TableRow.LayoutParams.MATCH_PARENT, 180, 1f
            )
            seats.add(mutableListOf())

            for (col in MIN_COLUMN..MAX_COLUMN) {
                val textView = TextView(this)
                textView.layoutParams = TableRow.LayoutParams(
                    TableRow.LayoutParams.MATCH_PARENT, 180, 1f
                )

                textView.text = getString(R.string.seat_name_form).format(row, col)
                textView.textSize = resources.getDimension(R.dimen.text_very_small)
                textView.setBackgroundColor(getColor(R.color.not_selected_seat_color))
                getSeatColorID(Seat.getSeatType(row)).let { if (it != null) textView.setTextColor(it) }
                textView.gravity = Gravity.CENTER

                seats[row - MIN_ROW].add(textView)
                tableRow.addView(textView)
            }

            binding.seatTableLayout.addView(tableRow)
        }

        return seats
    }

    private fun getSeatColorID(seatType: SeatType?): Int? = when (seatType) {
        SeatType.S -> getColor(R.color.seat_s)
        SeatType.A -> getColor(R.color.seat_a)
        SeatType.B -> getColor(R.color.seat_b)
        else -> null
    }

    private fun setSeatSelectEvent(seats: List<List<TextView>>) {
        seats.forEach {
            it.forEach { seat ->
                seat.setOnClickListener {
                    if (seat.isSelected) {
                        seat.setBackgroundColor(getColor(R.color.not_selected_seat_color))
                        seat.isSelected = false
                    } else if (!seat.isSelected) {
                        seat.setBackgroundColor(getColor(R.color.selected_seat_color))
                        seat.isSelected = true
                    }
                }
            }
        }
    }
}
