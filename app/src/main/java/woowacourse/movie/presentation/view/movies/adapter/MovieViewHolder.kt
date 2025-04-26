package woowacourse.movie.presentation.view.movies.adapter

import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import woowacourse.movie.R
import woowacourse.movie.presentation.extension.setImage
import woowacourse.movie.presentation.extension.toDateTimeFormatter
import woowacourse.movie.presentation.model.MovieUiModel

class MovieViewHolder(
    private val parentView: View,
    private val eventListener: OnMovieEventListener,
) : RecyclerView.ViewHolder(parentView) {
    private val ivPoster: ImageView = parentView.findViewById(R.id.iv_poster)
    private val tvTitle: TextView = parentView.findViewById(R.id.tv_title)
    private val tvDate: TextView = parentView.findViewById(R.id.tv_date)
    private val tvRunningTime: TextView = parentView.findViewById(R.id.tv_running_time)
    private val btnReservation: Button = parentView.findViewById(R.id.btn_reservation)

    private var movie: MovieUiModel? = null

    init {
        btnReservation.setOnClickListener {
            movie?.let { eventListener.onClick(it) }
        }
    }

    fun bind(movie: MovieUiModel) {
        this.movie = movie

        movie.poster.setImage(ivPoster)
        tvTitle.text = movie.title
        tvDate.text = getDate(movie)
        tvRunningTime.text = getRunningTime(movie)
    }

    private fun getDate(movie: MovieUiModel): String =
        movie.screeningPeriod.run {
            val format = parentView.context.getString(R.string.movie_screening_period_format)
            parentView.context.getString(
                R.string.movie_date,
                format.toDateTimeFormatter()?.let { formatter ->
                    startDate.format(formatter)
                },
                format.toDateTimeFormatter()?.let { formatter ->
                    endDate.format(formatter)
                },
            )
        }

    private fun getRunningTime(movie: MovieUiModel): String =
        parentView.context.getString(R.string.running_time, movie.runningTime.toString())
}
