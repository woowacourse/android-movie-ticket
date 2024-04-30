package woowacourse.movie.ui.screen.adapter

import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import woowacourse.movie.R
import woowacourse.movie.domain.model.ScreenAd
import woowacourse.movie.ui.toUi

class ScreenViewHolder(
    view: View,
    private val onReserveClick: (id: Int) -> Unit,
) : RecyclerView.ViewHolder(view) {
    private val poster: ImageView = view.findViewById(R.id.iv_poster)
    private val title: TextView = view.findViewById(R.id.tv_title)
    private val date: TextView = view.findViewById(R.id.tv_screen_date)
    private val runningTime: TextView = view.findViewById(R.id.tv_screen_running_time)
    private val reserveButton: Button = view.findViewById(R.id.btn_reserve_now)

    fun bind(screen: ScreenAd.ScreenPreviewUi) {
        with(screen) {
            poster.setImageResource(moviePreviewUI.image.imageSource as Int)
            title.text = moviePreviewUI.title
            this@ScreenViewHolder.date.text = this.dateRange.toUi()
            runningTime.text = moviePreviewUI.runningTime.toString()
            reserveButton.setOnClickListener {
                onReserveClick(screen.id)
            }
        }
    }
}
