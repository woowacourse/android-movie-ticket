package woowacourse.movie.view

import android.util.TypedValue
import android.view.Gravity
import android.view.ViewGroup
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import androidx.core.view.children
import domain.Seat
import domain.SeatCol
import domain.SeatRow
import domain.Seats
import woowacourse.movie.R
import woowacourse.movie.dto.SeatColDto
import woowacourse.movie.dto.SeatDto
import woowacourse.movie.dto.SeatRowDto
import woowacourse.movie.mapper.mapToSeat

class SeatSelectView(
    val viewGroup: ViewGroup,
    val onSeatClick: (Seat, TextView) -> Unit,
    val seats: Seats,
) {

    private val tableLayout = viewGroup.findViewById<TableLayout>(R.id.seat)

    var seatsView: List<List<TextView>> = mutableListOf()

    init {
        setSeatsView()
        onSeatClickListener()
    }

    private fun setSeatsView() {
        repeat(TABLE_ROW) { row ->
            val tableRow = TableRow(tableLayout.context)
            tableRow.layoutParams = TableLayout.LayoutParams(0, 0, SEAT_WEIGHT)

            repeat(TABLE_COL) { col ->
                tableRow.addView(getSeat(row + 1, col + 1))
            }
            tableLayout.addView(tableRow)
        }
        bindSeatsView()
    }

    private fun bindSeatsView() {
        seatsView = tableLayout
            .children
            .filterIsInstance<TableRow>()
            .map { it.children.filterIsInstance<TextView>().toList() }
            .toList()
    }

    private fun getSeat(row: Int, col: Int): TextView {
        val seat = SeatDto(SeatRowDto.of(row), SeatColDto(col))
        val textView = TextView(tableLayout.context)
        textView.text = seat.getString()
        textView.setTextColor(tableLayout.context.getColor(seat.row.getColor()))
        textView.gravity = Gravity.CENTER
        textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, SEAT_TEXT_SIZE)
        setSeatColor(seat.mapToSeat(), textView)
        textView.layoutParams =
            TableRow.LayoutParams(0, TableRow.LayoutParams.MATCH_PARENT, SEAT_WEIGHT)

        return textView
    }

    private fun setSeatColor(seat: Seat, textView: TextView) {
        if (seats.containsSeat(seat)) {
            textView.setBackgroundColor(viewGroup.context.getColor(R.color.select_seat))
        } else {
            textView.setBackgroundColor(viewGroup.context.getColor(R.color.white))
        }
    }

    private fun onSeatClickListener() {
        seatsView.forEachIndexed { rowIndex, rowView ->
            rowView.forEachIndexed { colIndex, textView ->
                textView.setOnClickListener {
                    val seat = Seat(
                        SeatRow(rowIndex + 1),
                        SeatCol(colIndex + 1),
                    )
                    onSeatClick(seat, textView)
                }
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
