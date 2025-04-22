package woowacourse.movie.adapter

import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import woowacourse.movie.global.toFormattedString
import java.time.LocalDate

class ReservationDaySpinnerAdapter(var items: List<LocalDate>) : BaseAdapter() {
    override fun getCount(): Int {
        return items.size
    }

    // 특정 위치의 데이터를 반환
    override fun getItem(position: Int): LocalDate {
        return items[position]
    }

    // 특정 위치의 아이템 ID를 반환 (일반적으로 position을 사용)
    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(
        position: Int,
        convertView: View?,
        parent: ViewGroup,
    ): View {
        val view: TextView = convertView as? TextView ?: TextView(parent.context)
        view.text = getItem(position).toFormattedString()
        return view
    }
}
