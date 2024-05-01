package woowacourse.movie.feature.main.adapter

import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import woowacourse.movie.R
import woowacourse.movie.feature.main.ui.ScreeningItem

class ScreeningViewHolder(
    val view: View,
    private val itemClickListener: ScreeningAdapter.OnItemClickListener,
) : RecyclerView.ViewHolder(view) {
    private val poster: ImageView = view.findViewById(R.id.list_img_poster)
    private val title: TextView = view.findViewById(R.id.list_movie_title)
    private val screeningPeriod: TextView = view.findViewById(R.id.screening_period)
    private val runningTime: TextView = view.findViewById(R.id.running_time)
    private val reservationButton: Button = view.findViewById(R.id.btn_reservation)

    fun bind(screening: ScreeningItem.ScreeningModel) {
        val screeningPeriodText = screening.getFormattedScreeningPeriod(view.context)
        val runningTimeText = screening.getFormattedRunningTime(view.context)
        with(this) {
            poster.setImageResource(screening.poster)
            title.text = screening.title
            screeningPeriod.text = screeningPeriodText
            runningTime.text = runningTimeText
            reservationButton.setOnClickListener {
                itemClickListener.onItemClick(screening.id)
            }
        }
    }
}
