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
        val screeningPeriod =
            getFormattedScreeningPeriod(screening)
        val runningTime =
            getFormattedRunningTime(screening)
        with(this) {
            poster.setImageResource(screening.poster)
            title.text = screening.title
            this.screeningPeriod.text = screeningPeriod
            this.runningTime.text = runningTime
            reservationButton.setOnClickListener {
                itemClickListener.onItemClick(screening.id)
            }
        }
    }

    private fun getFormattedScreeningPeriod(screening: ScreeningItem.ScreeningModel): String {
        return view.context.getString(
            R.string.screening_period,
            screening.releaseDate,
            screening.endDate,
        )
    }

    private fun getFormattedRunningTime(screening: ScreeningItem.ScreeningModel): String {
        return view.context.getString(
            R.string.running_time,
            screening.runningTime,
        )
    }
}
