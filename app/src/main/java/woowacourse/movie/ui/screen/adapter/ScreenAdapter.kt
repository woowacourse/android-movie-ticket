package woowacourse.movie.ui.screen.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import woowacourse.movie.R
import woowacourse.movie.ui.ScreenPreviewUI
import woowacourse.movie.ui.screen.OnScreenClickListener

class ScreenAdapter(
    private var item: List<ScreenPreviewUI>,
    private val onScreenClickListener: OnScreenClickListener,
) : BaseAdapter() {
    override fun getCount(): Int = item.size

    override fun getItem(position: Int): ScreenPreviewUI = item[position]

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getView(
        position: Int,
        convertView: View?,
        parent: ViewGroup,
    ): View {
        val viewHolder: ScreenViewHolder
        if (convertView == null) {
            viewHolder =
                ScreenViewHolder(
                    LayoutInflater.from(parent.context).inflate(R.layout.holder_screen, parent, false),
                    onScreenClickListener,
                )
            viewHolder.saveViewHolder()
        } else {
            viewHolder = convertView.viewHolder()
        }

        viewHolder.bind(item[position])
        return viewHolder.view
    }

    fun updateScreens(screens: List<ScreenPreviewUI>) {
        item = screens
        notifyDataSetChanged()
    }

    class ScreenViewHolder(
        val view: View,
        private val onScreenClickListener: OnScreenClickListener,
    ) {
        private val poster: ImageView = view.findViewById(R.id.iv_poster)
        private val title: TextView = view.findViewById(R.id.tv_title)
        private val date: TextView = view.findViewById(R.id.tv_screen_date)
        private val runningTime: TextView = view.findViewById(R.id.tv_screen_running_time)
        private val reserveButton: Button = view.findViewById(R.id.btn_reserve_now)

        fun saveViewHolder() {
            view.tag = this
        }

        fun bind(screen: ScreenPreviewUI) {
            initView(screen)
            initClickListener(screen)
        }

        private fun initClickListener(screen: ScreenPreviewUI) {
            reserveButton.setOnClickListener {
                onScreenClickListener.onClick(screen.id)
            }
        }

        private fun initView(screen: ScreenPreviewUI) {
            with(screen) {
                poster.setImageResource(moviePreviewUI.image.imageSource as Int)
                title.text = moviePreviewUI.title
                this@ScreenViewHolder.date.text = this.date
                runningTime.text = moviePreviewUI.runningTime.toString()
            }
        }
    }
}

private fun View.viewHolder(): ScreenAdapter.ScreenViewHolder = this.tag as ScreenAdapter.ScreenViewHolder
