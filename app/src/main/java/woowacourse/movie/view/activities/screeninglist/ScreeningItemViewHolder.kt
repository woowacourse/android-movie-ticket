package woowacourse.movie.view.activities.screeninglist

import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import woowacourse.movie.R
import java.time.format.DateTimeFormatter

class ScreeningItemViewHolder(
    private val view: View,
    private val onReserveButtonClick: (Long) -> Unit
) : RecyclerView.ViewHolder(view) {
    private val posterView: ImageView = view.findViewById(R.id.movie_poster_iv)
    private val titleView: TextView = view.findViewById(R.id.movie_title_tv)
    private val screeningRange: TextView = view.findViewById(R.id.screening_range_tv)
    private val runningTimeView: TextView = view.findViewById(R.id.running_time_tv)
    private val reserveButton: Button = view.findViewById(R.id.reserve_now_btn)

    fun bind(screeningUIState: ScreeningListViewItemUIState.ScreeningUIState) {
        posterView.setImageResource(screeningUIState.poster)
        titleView.text = screeningUIState.title
        screeningRange.text = view.resources.getString(R.string.screening_range_format)
            .format(
                DATE_FORMATTER.format(screeningUIState.screeningStartDate),
                DATE_FORMATTER.format(screeningUIState.screeningEndDate)
            )
        runningTimeView.text = view.resources.getString(R.string.running_time_format)
            .format(screeningUIState.runningTime)
        reserveButton.setOnClickListener { onReserveButtonClick(screeningUIState.screeningId) }
    }

    companion object {
        private val DATE_FORMATTER: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy.MM.dd")
    }
}
