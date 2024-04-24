package woowacourse.movie.ui.detail

import android.app.Activity
import android.widget.ImageView
import android.widget.TextView
import woowacourse.movie.R
import woowacourse.movie.ui.ScreenDetailUI

class ScreenDetailScreenView(activity: Activity) : ScreenDetailView {
    private val title: TextView = activity.findViewById(R.id.tv_title)
    private val date: TextView = activity.findViewById(R.id.tv_screen_date)
    private val runningTime: TextView = activity.findViewById(R.id.tv_screen_running_time)
    private val description: TextView = activity.findViewById(R.id.tv_description)
    private val poster: ImageView = activity.findViewById(R.id.iv_poster)

    override fun show(screen: ScreenDetailUI) {
        with(screen) {
            title.text = movieDetailUI.title
            this@ScreenDetailScreenView.date.text = date
            runningTime.text = movieDetailUI.runningTime.toString()
            description.text = movieDetailUI.description
            poster.setImageResource(movieDetailUI.image.imageSource as Int)
        }
    }
}
