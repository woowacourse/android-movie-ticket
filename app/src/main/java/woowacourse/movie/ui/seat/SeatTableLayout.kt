package woowacourse.movie.ui.seat

import android.content.Context
import android.util.AttributeSet
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat
import androidx.core.view.children
import woowacourse.movie.R
import woowacourse.movie.ticket.Position

// 좌석의 행과 열은 1부터 시작한다. 0부터 사용하지 않도록 조심하자
class SeatTableLayout(context: Context, attrs: AttributeSet) : TableLayout(context, attrs) {

    init {
        initSetting()
    }

    // 테이블 레이아웃 세팅
    private fun initSetting() {
        this.isStretchAllColumns = true
        this.isShrinkAllColumns = true
        this.setBackgroundColor(ContextCompat.getColor(context, R.color.white))
    }

    // 테이블 사이즈 세팅 및 생성
    fun setTable(rowSize: Int, columnSize: Int) {
        addRow(rowSize, columnSize)
        initText()
    }

    // 테이블 로우 생성
    private fun addRow(rowSize: Int, columnSize: Int) {
        repeat(rowSize) {
            addView(makeRow(columnSize).apply { id = R.string.seat_table_row + it })
        }
    }

    private fun makeRow(columnSize: Int): TableRow {
        val row = TableRow(context)
        row.layoutParams = TableRow.LayoutParams(
            TableRow.LayoutParams.MATCH_PARENT,
            TableRow.LayoutParams.MATCH_PARENT,
        )

        repeat(columnSize) {
            row.addView(makeTextView())
        }

        return row
    }

    private fun makeTextView(): TextView {
        val seat = TextView(context, null, 0, R.style.SeatTableText)
        seat.layoutParams = TableRow.LayoutParams(
            TableRow.LayoutParams.WRAP_CONTENT,
            TableRow.LayoutParams.WRAP_CONTENT,
        )
        seat.setBackgroundResource(R.drawable.seat_selector)
        return seat
    }

    // 자리 위치 텍스트 지정
    private fun initText() {
        this.loopTable { rowIdx, columnIdx, textView ->
            // 행과 열이 1부터 시작하지만 table은 인덱스를 제공하므로 1을 더해준다
            textView.text = SeatRow.find(rowIdx).name + (columnIdx + 1)
        }
    }

    // 자리 등급 영역 지정
    fun setColorRange(rangeMap: Map<List<IntRange>, Int>) {
        rangeMap.entries.forEach {
            initSeatColor(it.key, it.value)
        }
    }

    private fun initSeatColor(ranges: List<IntRange>, @ColorRes color: Int) {
        this.loopTable { rowIdx, _, textView ->
            if (ranges.any { rowIdx in it }) {
                val textColor = ContextCompat.getColor(context, color)
                textView.setTextColor(textColor)
            }
        }
    }

    fun setClickListener(action: (position: Position) -> Unit) {
        this.loopTable { rowIdx, columnIdx, view ->
            view.setOnClickListener {
                action(Position(rowIdx, columnIdx))
            }
        }
    }

    // 테이블 레이아웃 루프 확장함수
    private fun TableLayout.loopTable(action: (Int, Int, TextView) -> Unit) {
        this.children
            .filterIsInstance<TableRow>()
            .forEachIndexed { rowIdx, row ->
                row.loopRow { columnIdx, textView ->
                    action(rowIdx, columnIdx, textView)
                }
            }
    }

    private fun TableRow.loopRow(action: (Int, TextView) -> Unit) {
        children.filterIsInstance<TextView>()
            .forEachIndexed { columnIdx, textView ->
                action(columnIdx, textView)
            }
    }

    operator fun get(index: Int): TableRow {
        return this.children.filterIsInstance<TableRow>().toList()[index]
    }
}
