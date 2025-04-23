package woowacourse.movie.view.movie

import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import woowacourse.movie.R
import woowacourse.movie.domain.Movie
import java.time.format.DateTimeFormatter

class MovieViewHolder(
    private val view: View,
) {
    private val poster: ImageView = view.findViewById(R.id.poster)
    private val title: TextView = view.findViewById(R.id.movie_title)
    private val screeningDate: TextView = view.findViewById(R.id.screening_date)
    private val runningTime: TextView = view.findViewById(R.id.running_time)
    private val reserveButton: Button = view.findViewById(R.id.reserve_button)

    fun bind(
        item: Movie,
        onButtonClick: (Movie) -> Unit,
    ) {
        poster.setImageResource(item.poster)
        title.text = item.title
        screeningDate.text =
            view.context.getString(
                R.string.screening_date,
                item.startDate.format(DATE_FORMAT),
                item.endDate.format(DATE_FORMAT),
            )
        runningTime.text =
            view.context.getString(R.string.running_time, item.runningTime)

        reserveButton.setOnClickListener { onButtonClick(item) }
    }

    companion object {
        private val DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy.M.d")
    }
}
