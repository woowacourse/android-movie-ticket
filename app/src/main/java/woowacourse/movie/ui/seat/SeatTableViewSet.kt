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

class SeatTableViewSet(
    root: View,
    private val maxSelectCount: CountState
) : Subject {
    private val seats: TableLayout = root.findViewById(R.id.seats)
    val choosedSeatInfo: List<SeatPositionState>
        get() {
            return mutableListOf<SeatPositionState>().apply {
                getAllSeatView().forEachIndexed { index, seatView ->
                    if (seatView.isChoosed) add(convertIndexToPosition(index))
                }
            }
        }

    private val observers: MutableList<Observer> = mutableListOf()

    init {
        getAllSeatView().forEachIndexed { index, view ->
            view.setOnClickListener {
                onClick(view)
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

    private fun onClick(view: SeatView) {
        val oldChoosedCount = choosedSeatInfo.size
        if (view.isChoosed.not() && oldChoosedCount >= maxSelectCount.value) return
        view.toggle()
        notifyObserver(choosedSeatInfo)
    }

    fun chooseSeatUpdate(newChoosedPositions: List<SeatPositionState>) {
        clear()
        getAllSeatView().filterIndexed { index, seatView ->
            convertIndexToPosition(index) in newChoosedPositions
        }.forEach { it.toggle() }
        notifyObserver(choosedSeatInfo)
    }

    private fun clear() {
        getAllSeatView().filter { it.isChoosed }.forEach { it.toggle() } // 원본 상태로 되돌림
    }

    override fun registerObserver(observer: Observer) {
        observers.add(observer)
    }

    override fun removeObserver(observer: Observer) {
        observers.remove(observer)
    }

    override fun notifyObserver(positionState: List<SeatPositionState>) {
        observers.forEach { it.updateSelectSeats(positionState) }
    }

    companion object {
        private const val ROW_SIZE = 5
        private const val COLUMN_SIZE = 4
    }
}
