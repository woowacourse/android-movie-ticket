package woowacourse.movie.view.movies

import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import woowacourse.movie.R
import woowacourse.movie.domain.model.Movie
import woowacourse.movie.view.extension.toDateTimeFormatter

class MovieViewHolder(
    val view: View,
) {
    private val ivPoster = view.findViewById<ImageView>(R.id.iv_poster)
    private val tvTitle = view.findViewById<TextView>(R.id.tv_title)
    private val tvDate = view.findViewById<TextView>(R.id.tv_date)
    private val tvRunningTime = view.findViewById<TextView>(R.id.tv_running_time)
    private val btnReservation = view.findViewById<Button>(R.id.btn_reservation)

    fun bind(
        movie: Movie,
        eventListener: OnMovieEventListener,
    ) {
        ivPoster.setImageResource(movie.posterResId.toInt())
        tvTitle.text = movie.title
        tvDate.text =
            movie.screeningPeriod.run {
                view.context.getString(
                    R.string.movie_date,
                    MOVIE_SCREENING_PERIOD_FORMAT.toDateTimeFormatter()?.let { formatter ->
                        movie.screeningPeriod.startDate.format(formatter)
                    },
                    MOVIE_SCREENING_PERIOD_FORMAT.toDateTimeFormatter()?.let { formatter ->
                        movie.screeningPeriod.endDate.format(formatter)
                    },
                )
            }
        tvRunningTime.text = view.context.getString(R.string.running_time, movie.runningTime.minute.toString())
        btnReservation.setOnClickListener {
            eventListener.onReserveButtonClick(movie)
        }
    }

    companion object {
        private const val MOVIE_SCREENING_PERIOD_FORMAT = "yyyy.M.d"
    }
}
