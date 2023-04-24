package woowacourse.movie.viewholder

import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import domain.Movie
import woowacourse.movie.R
import woowacourse.movie.view.mapper.MovieMapper
import java.time.format.DateTimeFormatter

class MovieItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val posterImageView: ImageView
    private val titleTextView: TextView
    private val movieDateTextView: TextView
    private val runningTimeTextView: TextView
    private val reservationButton: Button

    init {
        posterImageView = view.findViewById(R.id.item_movie_poster)
        titleTextView = view.findViewById(R.id.item_movie_title)
        movieDateTextView = view.findViewById(R.id.item_movie_date)
        runningTimeTextView = view.findViewById(R.id.item_movie_running_time)
        reservationButton = view.findViewById(R.id.item_movie_reservation_button)
    }

    fun bind(movie: Movie, onClickEvent: (Movie) -> Unit) {
        val movieViewModel = MovieMapper.toUi(movie)
        posterImageView.setImageResource(movieViewModel.picture)
        val dateFormat =
            DateTimeFormatter.ofPattern(movieDateTextView.context.getString(R.string.movie_date_format))
        movieDateTextView.text = movieDateTextView.context.getString(R.string.movie_date).format(
            dateFormat.format(movieViewModel.startDate),
            dateFormat.format(movieViewModel.endDate)
        )
        runningTimeTextView.text =
            runningTimeTextView.context.getString(R.string.movie_running_time)
                .format(movieViewModel.runningTime)
        titleTextView.text = movieViewModel.title
        reservationButton.setOnClickListener { onClickEvent(movie) }
    }
}
