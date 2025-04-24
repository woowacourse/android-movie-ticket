package woowacourse.movie.booking.detail.adapter

import android.R
import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import woowacourse.movie.util.Formatter
import java.time.LocalTime

class ScreeningTimeSpinnerAdapter(
    context: Context,
    items: List<LocalTime>,
) : ArrayAdapter<LocalTime>(context, R.layout.simple_spinner_item, items) {
    init {
        setDropDownViewResource(R.layout.simple_spinner_item)
    }

    override fun getView(
        position: Int,
        convertView: View?,
        parent: ViewGroup,
    ): View {
        val view = super.getView(position, convertView, parent) as TextView
        val time = getItem(position)
        view.text = Formatter.formatTimeWithMidnight24(time!!)
        return view
    }

    override fun getDropDownView(
        position: Int,
        convertView: View?,
        parent: ViewGroup,
    ): View {
        val view = super.getDropDownView(position, convertView, parent) as TextView
        val time = getItem(position)
        view.text = Formatter.formatTimeWithMidnight24(time!!)
        return view
    }
}
