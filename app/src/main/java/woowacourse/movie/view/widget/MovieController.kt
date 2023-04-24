package woowacourse.movie.view.widget

import woowacourse.movie.R
import woowacourse.movie.view.data.MovieViewData
import java.time.format.DateTimeFormatter

object MovieController {
    fun bind(
        movie: MovieViewData,
        movieView: MovieView
    ) {
        movieView.poster?.setImageResource(movie.poster.resource)
        movieView.title?.text = movie.title

        if (movieView.date != null) {
            val dateFormat =
                DateTimeFormatter.ofPattern(movieView.date.context.getString(R.string.movie_date_format))
            movieView.date.text = movieView.date.context.getString(R.string.movie_date).format(
                dateFormat.format(movie.date.startDate), dateFormat.format(movie.date.endDate)
            )
        }

        if (movieView.runningTime != null) {
            movieView.runningTime.text =
                movieView.runningTime.context.getString(R.string.movie_running_time)
                    .format(movie.runningTime)
        }
        movieView.description?.text = movie.description
    }
}
