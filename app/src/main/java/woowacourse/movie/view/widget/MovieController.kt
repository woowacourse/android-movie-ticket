package woowacourse.movie.view.widget

import android.widget.ImageView
import android.widget.TextView
import woowacourse.movie.R
import woowacourse.movie.view.MovieViewData
import java.time.format.DateTimeFormatter

class MovieController(
    private val movie: MovieViewData,
    private val poster: ImageView? = null,
    private val title: TextView? = null,
    private val date: TextView? = null,
    private val runningTime: TextView? = null,
    private val description: TextView? = null
) {
    fun render() {
        poster?.setImageResource(movie.poster.resource)
        title?.text = movie.title

        if (date != null) {
            val dateFormat =
                DateTimeFormatter.ofPattern(date.context.getString(R.string.movie_date_format))
            date.text = date.context.getString(R.string.movie_date).format(
                dateFormat.format(movie.date.startDate), dateFormat.format(movie.date.endDate)
            )
        }

        if (runningTime != null) {
            runningTime.text =
                runningTime.context.getString(R.string.movie_running_time).format(movie.runningTime)
        }
        description?.text = movie.description
    }
}
