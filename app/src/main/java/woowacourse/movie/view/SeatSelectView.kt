package woowacourse.movie.view

import android.util.TypedValue
import android.view.Gravity
import android.view.ViewGroup
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import androidx.core.view.children
import domain.Position
import domain.Seat
import domain.Seats
import domain.TicketPrice
import woowacourse.movie.R
import woowacourse.movie.mapper.mapToUIModel

class SeatSelectView(
    val viewGroup: ViewGroup,
    val onSeatClick: (Seat, TextView) -> Unit,
    val seats: Seats,
) {

    private val tableLayout = viewGroup.findViewById<TableLayout>(R.id.seat)

    var seatsView: List<TextView> = mutableListOf()

    init {
        setSeatsView()
        onSeatClickListener()
    }

    private fun setSeatsView() {
        repeat(TABLE_ROW) { row ->
            val tableRow = TableRow(tableLayout.context)
            tableRow.layoutParams = TableLayout.LayoutParams(0, 0, SEAT_WEIGHT)

            repeat(TABLE_COL) { col ->
                tableRow.addView(setSeat(row + 1, col + 1))
            }
            tableLayout.addView(tableRow)
        }
        bindSeatsView()
    }

    private fun bindSeatsView() {
        seatsView = tableLayout
            .children
            .filterIsInstance<TableRow>()
            .flatMap { it.children }
            .filterIsInstance<TextView>()
            .toList()
    }

    private fun setSeat(row: Int, col: Int): TextView {
        val position = Position(row, col)
        val textView = TextView(tableLayout.context)
        textView.text = position.mapToUIModel().getPosition()
        textView.setTextColor(tableLayout.context.getColor(position.mapToUIModel().getColor()))
        textView.gravity = Gravity.CENTER
        textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, SEAT_TEXT_SIZE)
        setSeatColor(position, textView)
        textView.layoutParams =
            TableRow.LayoutParams(0, TableRow.LayoutParams.MATCH_PARENT, SEAT_WEIGHT)

        return textView
    }

    private fun setSeatColor(position: Position, textView: TextView) {
        if (seats.containsSeat(Seat(position, TicketPrice.of(position)))) {
            textView.setBackgroundColor(viewGroup.context.getColor(R.color.select_seat))
        } else {
            textView.setBackgroundColor(viewGroup.context.getColor(R.color.white))
        }
    }

    private fun onSeatClickListener() {
        seatsView.forEachIndexed { index, textView ->
            textView.setOnClickListener {
                val seat = Seat(Position.of(index), TicketPrice.of(Position.of(index)))
                onSeatClick(seat, textView)
            }
        }
    }

    companion object {
        private const val TABLE_ROW = 5
        private const val TABLE_COL = 4
        private const val SEAT_TEXT_SIZE = 22F
        private const val SEAT_WEIGHT = 1f
    }
}
