package woowacourse.movie.view.widget

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.widget.TableRow
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import woowacourse.movie.R
import woowacourse.movie.view.data.SeatViewData

class SeatView private constructor(
    context: Context,
    var isSeatSelected: Boolean = false
) : ConstraintLayout(context) {

    private fun initLayout() {
        LayoutInflater.from(context).inflate(R.layout.item_seat, this)

        layoutParams = TableRow.LayoutParams(0, LayoutParams.MATCH_PARENT, 1f)
        setBackgroundColor(Color.WHITE)
    }

    private fun initText(seat: SeatViewData) {
        val textView = findViewById<TextView>(R.id.item_seat_text)
        textView.text = context.getString(
            R.string.seat_row_column, seat.rowCharacter, seat.column + COLUMN_FIXER
        )
        textView.setTextColor(seat.color)
    }

    private fun selectSeat(selectable: () -> Boolean) {
        if (!isSeatSelected && selectable()) {
            isSeatSelected = true
            setBackgroundColor(context.getColor(R.color.selected_seat))
        } else if (isSeatSelected) {
            isSeatSelected = false
            setBackgroundColor(Color.WHITE)
        }
    }

    companion object {
        private const val COLUMN_FIXER = 1
        fun from(context: Context, seat: SeatViewData, selectable: () -> Boolean): SeatView {
            return SeatView(context).apply {
                initLayout()
                initText(seat)
                setOnClickListener {
                    selectSeat(selectable)
                }
            }
        }
    }
}
