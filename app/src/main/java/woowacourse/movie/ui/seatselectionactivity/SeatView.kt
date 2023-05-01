package woowacourse.movie.ui.seatselectionactivity

import android.view.Gravity
import android.widget.TableRow
import android.widget.TextView
import movie.domain.seat.RowSeat
import woowacourse.movie.R

class SeatView(val view: TextView, val row: Int, val col: Int, val onClick: (SeatView) -> Unit) {
    init {
        initViewStyle()
    }

    private fun initViewStyle() {
        view.layoutParams = TableRow.LayoutParams(
            TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.MATCH_PARENT, 1f
        )
        view.gravity = Gravity.CENTER
        view.textSize = 22f
        view.text = RowSeat.valueOf(row).name + col.toString()
        view.setOnClickListener { onClick(this) }
        view.setBackgroundResource(R.drawable.selector_seat)
        val color = when(row) {
            0, 1 -> view.context.getColor(R.color.purple_seat)
            2, 3 -> view.context.getColor(R.color.green_seat)
            4 -> view.context.getColor(R.color.blue_seat)
            else -> throw IllegalStateException(ROW_NOT_FOUND_ERROR)
        }
        view.setTextColor(color)
    }


    companion object {
        private const val ROW_NOT_FOUND_ERROR = "일치하는 row를 찾을 수 없습니다."
    }
}