package woowacourse.movie.view.movies

import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import woowacourse.movie.R
import woowacourse.movie.domain.model.Movie
import java.time.format.DateTimeFormatter

class MovieViewHolder(
    val view: View,
) : RecyclerView.ViewHolder(view) {
    private val ivPoster = view.findViewById<ImageView>(R.id.iv_poster)
    private val tvTitle = view.findViewById<TextView>(R.id.tv_title)
    private val tvDate = view.findViewById<TextView>(R.id.tv_date)
    private val tvRunningTime = view.findViewById<TextView>(R.id.tv_running_time)
    private val btnReservation = view.findViewById<Button>(R.id.btn_reservation)

    fun bind(
        movie: Movie,
        eventListener: OnMovieEventListener,
    ) {
        val format = view.context.getString(R.string.movie_screening_period_format)
        ivPoster.setImageResource(movie.poster.toInt())
        tvTitle.text = movie.title
        tvDate.text =
            movie.screeningPeriod.run {
                view.context.getString(
                    R.string.movie_date,
                    movie.screeningPeriod.startDate.format(DateTimeFormatter.ofPattern(format)),
                    movie.screeningPeriod.endDate.format(DateTimeFormatter.ofPattern(format)),
                )
            }
        tvRunningTime.text = view.context.getString(R.string.running_time, movie.runningTime.minute.toString())
        btnReservation.setOnClickListener {
            eventListener.onReserveButtonClick(movie)
        }
    }
}
