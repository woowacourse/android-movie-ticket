package woowacourse.movie.ui.detail.view

import android.content.Context
import android.util.AttributeSet
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import woowacourse.movie.R
import woowacourse.movie.ui.ScreenDetailUI
import woowacourse.movie.ui.toUi

class ScreenDetailScreenView(context: Context, attrs: AttributeSet? = null) : ScreenDetailView,
    ConstraintLayout(context, attrs) {
    private val title: TextView by lazy { findViewById(R.id.tv_title) }
    private val runningTime: TextView by lazy { findViewById(R.id.tv_screen_running_time) }
    private val description: TextView by lazy { findViewById(R.id.tv_description) }
    private val poster: ImageView by lazy { findViewById(R.id.iv_poster) }
    private val dateRange: TextView by lazy { findViewById(R.id.tv_screen_date) }

    init {
        inflate(context, R.layout.holder_screen_detail_screen, this)
    }

    override fun show(screen: ScreenDetailUI) {
        with(screen) {
            title.text = movieDetailUI.title
            runningTime.text = movieDetailUI.runningTime.toString()
            description.text = movieDetailUI.description
            poster.setImageResource(movieDetailUI.image.imageSource as Int)
            this@ScreenDetailScreenView.dateRange.text = dateRange.toUi()
        }
    }
}
