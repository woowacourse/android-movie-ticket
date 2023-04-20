package woowacourse.movie.activity.seatselect

import android.view.ViewGroup
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import androidx.core.view.children
import woowacourse.movie.R

class SeatView(viewGroup: ViewGroup) {
    private val _selectSeats = mutableListOf<String>()
    val selectSeats: List<String>
        get() = _selectSeats.toList()

    private val seatViews: List<TextView> = viewGroup.findViewById<TableLayout>(R.id.layout_seats)
        .children
        .filterIsInstance<TableRow>()
        .flatMap { it.children }
        .filterIsInstance<TextView>().toList()

    fun set() {
        setSeatViews()
    }

    private fun setSeatViews() {
        seatViews.forEach { textView ->
            textView.setOnClickListener {
                val seatId = textView.text.toString()
                if (_selectSeats.contains(seatId)) {
                    textView.setBackgroundColor(textView.context.getColor(R.color.white))
                    _selectSeats.remove(seatId)
                    return@setOnClickListener
                }
                textView.setBackgroundColor(textView.context.getColor(R.color.select_seat))
                _selectSeats.add(seatId)
            }
        }
    }
}
