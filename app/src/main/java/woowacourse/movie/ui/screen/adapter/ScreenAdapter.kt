package woowacourse.movie.ui.screen.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import woowacourse.movie.R
import woowacourse.movie.model.Screen

class ScreenAdapter(private val item: List<Screen>) : BaseAdapter() {
    override fun getCount(): Int = item.size

    override fun getItem(position: Int): Screen = item[position]

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getView(
        position: Int,
        convertView: View?,
        parent: ViewGroup,
    ): View {
        var view = convertView

        if (view == null) {
            view = LayoutInflater.from(parent.context).inflate(R.layout.holder_screen, parent, false)
        }

        val poster = view!!.findViewById<ImageView>(R.id.iv_poster)
        val title = view.findViewById<TextView>(R.id.tv_title)
        val date = view.findViewById<TextView>(R.id.tv_screen_date)
        val runningTime = view.findViewById<TextView>(R.id.tv_screen_running_time)

        poster.setImageResource(item[position].movie.imageSrc)
        title.text = item[position].movie.title
        date.text = item[position].date
        runningTime.text = item[position].movie.runningTime.toString()

        return view
    }
}
