package woowacourse.movie

import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import woowacourse.movie.Utils.toDotFormat
import java.time.LocalDate

class BookedDateSpinnerAdapter(val value: List<LocalDate>) : BaseAdapter() {
    override fun getCount(): Int {
        return value.size
    }

    override fun getItem(position: Int): LocalDate {
        return value[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(
        position: Int,
        convertView: View?,
        parent: ViewGroup
    ): View {
        val view: TextView

        if (convertView == null) {
            view = TextView(parent.context)
            view.text = view.context.getString(
                R.string.movie_running_dateTime,
                getItem(position).toDotFormat()
            )
        } else {
            view = convertView as TextView
        }

        return view
    }
}
