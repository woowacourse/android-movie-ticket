package woowacourse.movie

import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import woowacourse.movie.domain.Movie

class MovieItemViewHolder(
    view: View,
) {
    private val title: TextView = view.findViewById(R.id.tv_movie_title)
    private val poster: ImageView = view.findViewById(R.id.iv_movie_poster)
    private val date: TextView = view.findViewById(R.id.tv_movie_date)
    private val runningTime: TextView = view.findViewById(R.id.tv_movie_running_time)
    private val bookingBtn: Button = view.findViewById(R.id.btn_movie_booking)

    fun bind(
        movie: Movie,
        onBookingClick: (Movie) -> Unit,
    ) {
        title.text = movie.title
        poster.setImageResource(movie.poster)
        date.text =
            date.context.getString(
                R.string.movies_movie_date_with_tilde,
                movie.startDate,
                movie.endDate,
            )
        runningTime.text =
            runningTime.context.getString(
                R.string.movies_movie_running_time,
                movie.runningTime,
            )
        bookingBtn.setOnClickListener { onBookingClick(movie) }
    }
}
