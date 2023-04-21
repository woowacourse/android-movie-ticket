package woowacourse.movie.ui.seatselection

import android.view.View
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import androidx.core.view.children
import woowacourse.movie.model.ReservationUI
import woowacourse.movie.model.TicketCountUI
import woowacourse.movie.model.seat.ColUI
import woowacourse.movie.model.seat.RowUI
import woowacourse.movie.model.seat.SeatPositionUI

class SeatTable(
    value: TableLayout,
    private val reservationUI: ReservationUI,
    val setButtonEnabled: (Boolean) -> Unit,
    val setTicket: (SeatPositionUI) -> Unit
) {
    private val seatView: List<List<TextView>> =
        value.children
            .filterIsInstance<TableRow>()
            .map { it.children.filterIsInstance<TextView>().toList() }.toList()

    private var selectedCount = TicketCountUI()

    init {
        initTableClickListener()
    }

    private fun initTableClickListener() {
        seatView.forEachIndexed { row, rowTexts ->
            rowTexts.forEachIndexed { col, textView ->
                textView.setOnClickListener { view ->
                    val position = SeatPositionUI(RowUI(row), ColUI(col))
                    view.setSeatOnClick(position)
                }
            }
        }
    }

    private fun View.setSeatOnClick(seatPosition: SeatPositionUI) {
        when (isSelected) {
            true -> {
                isSelected = !isSelected
                --selectedCount
                setButtonEnabled(false)
                setTicket(seatPosition)
            }
            else -> {
                if (selectedCount != reservationUI.ticketCount) {
                    isSelected = !isSelected
                    ++selectedCount
                    setTicket(seatPosition)
                }
                if (selectedCount == reservationUI.ticketCount) {
                    setButtonEnabled(true)
                }
            }
        }
    }
}
