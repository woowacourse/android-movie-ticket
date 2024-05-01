package woowacourse.movie.presentation.movie_detail.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import woowacourse.movie.R
import woowacourse.movie.model.schedule.ScreeningDateTime

class TimeSpinnerAdapter(
    private val timeList: List<ScreeningDateTime>,
) : BaseAdapter() {
    override fun getCount(): Int = timeList.size

    override fun getItem(position: Int): ScreeningDateTime = timeList[position]

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getView(
        position: Int,
        convertView: View?,
        parent: ViewGroup,
    ): View {
        val view =
            convertView
                ?: LayoutInflater.from(parent.context).inflate(
                    R.layout.simple_spinner_item,
                    parent,
                    false,
                )
        val spinnerText =
            if (convertView == null) {
                val textView = view.findViewById<TextView>(R.id.simple_spinner_text)
                view.tag = textView
                textView
            } else {
                view.tag as TextView
            }
        spinnerText.text = timeList[position].dateTime.toString()
        return view
    }
}
