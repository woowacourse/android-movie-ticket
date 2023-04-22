package woowacourse.movie.view

import android.widget.ImageView
import android.widget.TextView
import woowacourse.movie.R
import woowacourse.movie.view.model.MovieViewModel
import java.time.format.DateTimeFormatter

class MovieView(
    private val poster: ImageView? = null,
    private val title: TextView? = null,
    private val date: TextView? = null,
    private val runningTime: TextView? = null,
    private val description: TextView? = null
) {
    fun render(movieViewModel: MovieViewModel) {
        poster?.setImageResource(movieViewModel.picture)
        title?.text = movieViewModel.title

        val dateFormat =
            DateTimeFormatter.ofPattern(title?.context?.getString(R.string.movie_date_format))
        date?.text = date?.context?.getString(R.string.movie_date)?.format(
            dateFormat.format(movieViewModel.startDate), dateFormat.format(movieViewModel.endDate)
        )
        runningTime?.text =
            runningTime?.context?.getString(R.string.movie_running_time)
                ?.format(movieViewModel.runningTime)
        description?.text = movieViewModel.description
    }
}
