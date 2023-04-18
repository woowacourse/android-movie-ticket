package woowacourse.movie.viewholder

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import woowacourse.movie.R
import woowacourse.movie.uimodel.MovieModel
import java.time.format.DateTimeFormatter

class MovieItemViewHolder(
    private val view: View,
) {
    private val moviePosterImageView: ImageView = view.findViewById(R.id.movie_poster_image_view)
    private val movieNameTextView: TextView = view.findViewById(R.id.movie_name_text_view)
    private val screeningDateTextView: TextView = view.findViewById(R.id.movie_screening_period_text_view)
    private val runningTimeTextView: TextView = view.findViewById(R.id.movie_running_time_text_view)
    private val dateFormat: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy.MM.dd")

    fun setViewContents(movieModel: MovieModel) {
        setPosterResource(movieModel)
        setMovieNameText(movieModel)
        setScreeningDateText(movieModel)
        setRunningTimeText(movieModel)
    }

    private fun setPosterResource(movieModel: MovieModel) {
        movieModel.posterImage?.let { moviePosterImageView.setImageResource(it) }
    }

    private fun setMovieNameText(movieModel: MovieModel) {
        movieNameTextView.text = movieModel.name.value
    }

    private fun setScreeningDateText(movieModel: MovieModel) {
        screeningDateTextView.text =
            view.context?.getString(R.string.screening_period_form)?.format(
                movieModel.screeningPeriod.startDate.format(dateFormat),
                movieModel.screeningPeriod.endDate.format(dateFormat)
            )
    }

    private fun setRunningTimeText(movieModel: MovieModel) {
        runningTimeTextView.text =
            view.context?.getString(R.string.running_time_form)
                ?.format(movieModel.runningTime)
    }
}
