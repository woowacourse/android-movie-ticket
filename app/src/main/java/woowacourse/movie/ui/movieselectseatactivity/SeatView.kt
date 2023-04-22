package woowacourse.movie.ui.movieselectseatactivity

import android.view.ViewGroup
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import woowacourse.movie.R
import woowacourse.movie.domain.grade.Position
import woowacourse.movie.util.getColor

class SeatView(private val view: TableLayout) {

    init {
        initTableLayoutItem()
    }

    fun initTableLayoutItem() {
        (Position.START_INDEX..Position.MAXIMUM_ROW_INDEX).forEach {
            view.addView(getTableRow())
        }
    }

    private fun getTableRow(): TableRow {
        val tableRowParams: TableLayout.LayoutParams =
            TableLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )

        return TableRow(view.context).apply {
            layoutParams = tableRowParams
            (Position.START_INDEX..Position.MAXIMUM_COLUMN_INDEX).forEach {
                setBackgroundColor(view.getColor(R.color.white))
                addView(getSeatTextView())
            }
        }
    }

    private fun getSeatTextView(): TextView {
        val seatTextViewParams: TableRow.LayoutParams =
            TableRow.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )

        return TextView(view.context).apply {
            layoutParams = seatTextViewParams
            text = "1"
        }
    }
}
