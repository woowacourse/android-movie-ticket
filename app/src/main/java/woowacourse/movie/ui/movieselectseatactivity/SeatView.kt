package woowacourse.movie.ui.movieselectseatactivity

import android.view.Gravity
import android.view.ViewGroup
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import androidx.annotation.Dimension
import woowacourse.movie.R
import woowacourse.movie.domain.grade.Grade
import woowacourse.movie.domain.grade.Position
import woowacourse.movie.util.getColor

class SeatView(private val view: TableLayout) {

    init {
        initTableLayoutItem()
    }

    private fun initTableLayoutItem() {
        (Position.START_INDEX..Position.MAXIMUM_ROW_INDEX).forEach {
            view.addView(getTableRow(it))
        }
    }

    private fun getTableRow(rowIndex: Int): TableRow {
        val tableRowParams: TableLayout.LayoutParams =
            TableLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.MATCH_PARENT,
                1f
            )

        return TableRow(view.context).apply {
            layoutParams = tableRowParams
            (Position.START_INDEX..Position.MAXIMUM_COLUMN_INDEX).forEach {
                setBackgroundColor(view.getColor(R.color.white))
                addView(getSeatTextView(rowIndex, it))
            }
        }
    }

    private fun getSeatTextView(rowIndex: Int, columnIndex: Int): TextView {
        val seatTextViewParams: TableRow.LayoutParams =
            TableRow.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )

        return TextView(view.context).apply {
            layoutParams = seatTextViewParams
            gravity = Gravity.CENTER
            setTextSize(Dimension.SP, 22f)
            text = String.format("%s%d", convertIndexToAlphabet(rowIndex), columnIndex + 1)
            setTextColor(view.getColor(getSeatTextColor(rowIndex)))
        }
    }

    private fun getSeatTextColor(rowIndex: Int): Int {
        return when (Grade.getGrade(rowIndex)) {
            Grade.S -> R.color.woowa_grade_s
            Grade.A -> R.color.woowa_grade_a
            Grade.B -> R.color.woowa_grade_b
        }
    }

    private

    fun convertIndexToAlphabet(index: Int): Char {
        val baseNumber = 65
        return (index + baseNumber).toChar()
    }
}
