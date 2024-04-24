package woowacourse.movie.presentation.ui.screen.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import woowacourse.movie.R
import woowacourse.movie.domain.model.Screen
import woowacourse.movie.presentation.ui.screen.ScreenActionHandler

class ScreenAdapter(
    private val screenActionHandler: ScreenActionHandler,
    private var item: List<Screen> = emptyList(),
) : BaseAdapter() {
    override fun getCount(): Int = item.size

    override fun getItem(position: Int): Screen = item[position]

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getView(
        position: Int,
        convertView: View?,
        parent: ViewGroup,
    ): View {
        val view: View
        val viewHolder: ViewHolder

        if (convertView == null) {
            view =
                LayoutInflater.from(parent.context).inflate(R.layout.holder_screen, parent, false)
            viewHolder = ViewHolder(view)
            view.tag = viewHolder
        } else {
            view = convertView
            viewHolder = view.tag as ViewHolder
        }

        viewHolder.bind(item[position])

        return view
    }

    fun updateScreens(screens: List<Screen>) {
        item = screens
        notifyDataSetChanged()
    }

    inner class ViewHolder(val view: View) {
        private val poster: ImageView = view.findViewById(R.id.iv_poster)
        private val title: TextView = view.findViewById(R.id.tv_title)
        private val date: TextView = view.findViewById(R.id.tv_screen_date)
        private val runningTime: TextView = view.findViewById(R.id.tv_screen_running_time)
        private val reserveButton: Button = view.findViewById(R.id.btn_reserve_now)

        fun bind(screen: Screen) {
            initView(screen)
            initClickListener(screen)
        }

        private fun initView(screen: Screen) {
            with(screen) {
                poster.setImageResource(movie.imageSrc)
                title.text = movie.title
                this@ViewHolder.date.text =
                    view.context.getString(R.string.screening_period, startDate, endDate)
                runningTime.text = view.context.getString(R.string.running_time, movie.runningTime)
            }
        }

        private fun initClickListener(screen: Screen) {
            reserveButton.setOnClickListener {
                screenActionHandler.onScreenClick(screen.id)
            }
        }
    }
}
