package woowacourse.movie.ui.seat

import android.util.Log
import android.view.View
import android.widget.TableLayout
import android.widget.TableRow
import androidx.core.view.children
import woowacourse.movie.R
import woowacourse.movie.model.CountState
import woowacourse.movie.model.SeatPositionState
import woowacourse.movie.ui.customView.SeatView

class SeatTable(
    root: View,
    private val maxSelectCount: CountState,
    private val update: (List<SeatPositionState>) -> Unit
) {
    private val seats: TableLayout = root.findViewById(R.id.seats)
    val chosenSeatInfo: List<SeatPositionState>
        get() {
            return mutableListOf<SeatPositionState>().apply {
                getAllSeatView().forEachIndexed { index, seatView ->
                    if (seatView.isChosen) add(convertIndexToPosition(index))
                }
            }
        }

    init {
        getAllSeatView().forEachIndexed { _, view ->
            view.setOnClickListener {
                clickSeat(view)
            }
        }
    }

    private fun getAllSeatView(): List<SeatView> {
        return seats
            .children
            .filterIsInstance<TableRow>()
            .flatMap { it.children }
            .filterIsInstance<SeatView>()
            .toList()
    }

    private fun convertIndexToPosition(index: Int): SeatPositionState {
        val row = index / COLUMN_SIZE + 1
        val column = (index - (row - 1) * COLUMN_SIZE) + 1
        Log.d("mendel", "index: $index , column: $column , row: $row")
        return SeatPositionState(row, column)
    }

    private fun clickSeat(view: SeatView) {
        val oldChosenCount = chosenSeatInfo.size
        if (view.isChosen.not() && oldChosenCount >= maxSelectCount.value) return
        view.toggle()
        update(chosenSeatInfo)
    }

    fun chosenSeatUpdate(newChosenPositions: List<SeatPositionState>) {
        clear()
        getAllSeatView().filterIndexed { index, _ ->
            convertIndexToPosition(index) in newChosenPositions
        }.forEach { it.toggle() }
        update(chosenSeatInfo)
    }

    private fun clear() {
        getAllSeatView().filter { it.isChosen }.forEach { it.toggle() } // 원본 상태로 되돌림
        update(chosenSeatInfo)
    }

    companion object {
        private const val ROW_SIZE = 5
        private const val COLUMN_SIZE = 4
    }
}
