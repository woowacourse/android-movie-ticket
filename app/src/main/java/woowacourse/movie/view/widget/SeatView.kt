package woowacourse.movie.view.widget

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.widget.TableRow
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import woowacourse.movie.R
import woowacourse.movie.view.data.SeatViewData

class SeatView(context: Context, seat: SeatViewData, var isSeatSelected: Boolean = false) :
    ConstraintLayout(context) {
    init {
        initLayout()
        initText(seat)
        setOnClickListener {
            selectSeat()
        }
    }

    private fun initLayout() {
        LayoutInflater.from(context).inflate(R.layout.item_seat, this)

        layoutParams = TableRow.LayoutParams(0, LayoutParams.MATCH_PARENT, 1f)
        setBackgroundColor(Color.WHITE)
    }

    private fun initText(seat: SeatViewData) {
        val textView = findViewById<TextView>(R.id.item_seat_text)
        textView.text =
            context.getString(
                R.string.seat_row_column,
                seat.rowCharacter,
                seat.column + COLUMN_FIXER
            )
        textView.setTextColor(seat.color)
    }

    private fun selectSeat() {
        isSeatSelected = !isSeatSelected
        if (isSeatSelected) {
            setBackgroundColor(Color.parseColor("#FAFF00"))
        } else {
            setBackgroundColor(Color.WHITE)
        }
    }

    companion object {
        private const val COLUMN_FIXER = 1
    }
}
