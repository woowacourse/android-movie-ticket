package woowacourse.movie.ui.screen.adapter

import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import woowacourse.movie.R
import woowacourse.movie.ui.ScreenPreviewUI
import woowacourse.movie.ui.screen.OnScreenClickListener

class ScreenViewHolder(
    val view: View,
    private val onScreenClickListener: OnScreenClickListener,
) {
    private val poster: ImageView = view.findViewById(R.id.iv_poster)
    private val title: TextView = view.findViewById(R.id.tv_title)
    private val date: TextView = view.findViewById(R.id.tv_screen_date)
    private val runningTime: TextView = view.findViewById(R.id.tv_screen_running_time)
    private val reserveButton: Button = view.findViewById(R.id.btn_reserve_now)

    fun bind(screen: ScreenPreviewUI) {
        initView(screen)
        initClickListener(screen)
    }

    private fun initView(screen: ScreenPreviewUI) {
        with(screen) {
            poster.setImageResource(moviePreviewUI.image.imageSource as Int)
            title.text = moviePreviewUI.title
            this@ScreenViewHolder.date.text = this.date
            runningTime.text = moviePreviewUI.runningTime.toString()
        }
    }

    private fun initClickListener(screen: ScreenPreviewUI) {
        reserveButton.setOnClickListener {
            onScreenClickListener.onClick(screen.id)
        }
    }
}
