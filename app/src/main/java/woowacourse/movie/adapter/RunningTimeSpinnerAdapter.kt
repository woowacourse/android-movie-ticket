package woowacourse.movie.adapter

import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import java.time.LocalTime
import java.time.format.DateTimeFormatter

class RunningTimeSpinnerAdapter(items: List<LocalTime>) : BaseAdapter() {
    private var mItems = items.toList()

    override fun getCount(): Int {
        return mItems.size
    }

    // 특정 위치의 데이터를 반환
    override fun getItem(position: Int): Any {
        return mItems[position]
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
        view.text = DateTimeFormatter.ofPattern("HH:mm").format(mItems[position])
        return view
    }

    fun changeItems(items: List<LocalTime>) {
        mItems = items.toList()
        notifyDataSetChanged()
    }
}
