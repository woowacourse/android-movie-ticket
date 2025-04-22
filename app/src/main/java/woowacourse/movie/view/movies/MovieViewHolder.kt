package woowacourse.movie.view.movies

import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import woowacourse.movie.R
import woowacourse.movie.domain.model.Movie
import woowacourse.movie.domain.model.Poster
import woowacourse.movie.view.extension.toDateTimeFormatter

class MovieViewHolder(
    private val parentView: View,
    private val eventListener: OnMovieEventListener,
) {
    private val ivPoster: ImageView = parentView.findViewById(R.id.iv_poster)
    private val tvTitle: TextView = parentView.findViewById(R.id.tv_title)
    private val tvDate: TextView = parentView.findViewById(R.id.tv_date)
    private val tvRunningTime: TextView = parentView.findViewById(R.id.tv_running_time)
    private val btnReservation: Button = parentView.findViewById(R.id.btn_reservation)

    fun bind(movie: Movie) {
        setPoster(movie)
        tvTitle.text = movie.title
        tvDate.text = getDate(movie)
        tvRunningTime.text = getRunningTime(movie)

        btnReservation.setOnClickListener {
            eventListener.onClick(movie)
        }
    }

    private fun getDate(movie: Movie): String =
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

    private fun getRunningTime(movie: Movie): String =
        parentView.context.getString(R.string.running_time, movie.runningTime.minute.toString())

    private fun setPoster(movie: Movie) {
        when (val poster = movie.poster) {
            is Poster.Resource -> ivPoster.setImageResource(poster.resId)
            is Poster.Url -> { // 이미지 로드
            }
        }
    }
}
