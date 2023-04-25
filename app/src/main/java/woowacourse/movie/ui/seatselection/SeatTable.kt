package woowacourse.movie.ui.seatselection

import android.content.Context
import android.view.View
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import androidx.core.view.children
import com.woowacourse.movie.domain.seat.Rank
import woowacourse.movie.R
import woowacourse.movie.model.TicketCountUI
import woowacourse.movie.model.mapper.seat.toSeatPosition
import woowacourse.movie.model.seat.ColUI
import woowacourse.movie.model.seat.RowUI
import woowacourse.movie.model.seat.SeatPositionUI

class SeatTable(
    value: TableLayout,
    private val ticketCount: TicketCountUI,
    val onEnabledChange: (Boolean) -> Unit,
    val onTicketCreated: (SeatPositionUI) -> Unit
) {
    private val seatView: List<List<TextView>> =
        value.children
            .filterIsInstance<TableRow>()
            .map { it.children.filterIsInstance<TextView>().toList() }.toList()

    private val seat = mutableSetOf<SeatPositionUI>()

    init {
        initSeatTable()
    }

    private fun initSeatTable() {
        seatView.forEachIndexed { row, rowTexts ->
            rowTexts.forEachIndexed { col, textView ->
                val position = SeatPositionUI(RowUI(row), ColUI(col))
                setText(position, textView)
                textView.setOnClickListener { view ->
                    view.setSeatOnClick(position)
                }
            }
        }
    }

    private fun setText(seatPosition: SeatPositionUI, view: TextView) {
        view.text = seatPosition.getSeatPositionDisplay()
        view.setTextColor(getTextColor(Rank.valueOf(seatPosition.toSeatPosition()), view.context))
    }

    private fun getTextColor(rank: Rank, context: Context): Int {
        val colorResId = when (rank) {
            Rank.B -> R.color.seat_b_rank_color
            Rank.A -> R.color.seat_a_rank_color
            Rank.S -> R.color.seat_s_rank_color
        }
        return context.getColor(colorResId)
    }

    private fun View.setSeatOnClick(seatPosition: SeatPositionUI) {
        when (isSelected) {
            true -> {
                setSeatPosition(seatPosition)
                onEnabledChange(false)
            }
            else -> {
                if (seat.size != ticketCount.count) {
                    setSeatPosition(seatPosition)
                }
            }
        }
    }

    fun setSeatPosition(seatPosition: SeatPositionUI) {
        with(seatView[seatPosition.row.x][seatPosition.col.y]) {
            if (isSelected)
                seat.remove(seatPosition)
            else
                seat.add(seatPosition)
            isSelected = !isSelected
            onTicketCreated(seatPosition)
            if (seat.size == ticketCount.count) {
                onEnabledChange(true)
            }
        }
    }
}
