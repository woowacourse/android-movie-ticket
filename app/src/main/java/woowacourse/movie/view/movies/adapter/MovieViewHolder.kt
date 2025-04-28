package woowacourse.movie.view.movies.adapter

import android.content.Context
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import woowacourse.movie.R
import woowacourse.movie.domain.Movie
import woowacourse.movie.domain.movietime.Date
import woowacourse.movie.view.movies.OnMovieEventListener
import java.time.format.DateTimeFormatter

class MovieViewHolder(
    private val view: View,
    eventListener: OnMovieEventListener,
) {
    private val movieImage: ImageView = view.findViewById(R.id.iv_movie_image)
    private val movieTitle: TextView = view.findViewById(R.id.tv_movie_title)
    private val movieDate: TextView = view.findViewById(R.id.tv_movie_date)
    private val movieTime: TextView = view.findViewById(R.id.tv_movie_time)
    private val reserveButton: Button = view.findViewById(R.id.btn_reserve)
    private var movie: Movie? = null

    init {
        reserveButton.setOnClickListener {
            movie?.let { eventListener.onClickReservation(it) }
        }
    }

    fun bind(movie: Movie) {
        this.movie = movie
        movieImage.setImageResource(movie.image)
        movieTitle.text = movie.title
        setDateTextView(movie.date, view.context)
        setTimeTextView(movie.time, view.context)
    }

    private fun setDateTextView(
        date: Date,
        context: Context?,
    ) {
        val formatter = DateTimeFormatter.ofPattern(DATETIME_PATTERN)
        val startDateFormatted = date.startDate.format(formatter)
        val endDateFormatted = date.endDate.format(formatter)
        movieDate.text =
            context?.getString(R.string.movieDate, startDateFormatted, endDateFormatted)
    }

    private fun setTimeTextView(
        time: Int,
        context: Context?,
    ) {
        movieTime.text =
            context?.getString(R.string.movieTime, time.toString())
    }

    companion object {
        private const val DATETIME_PATTERN = "yyyy.M.d"
    }
}
