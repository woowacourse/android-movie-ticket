package woowacourse.movie.view.movieSelection

import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import woowacourse.movie.R
import woowacourse.movie.view.model.movie.MovieListItem.MovieUiModel
import java.time.format.DateTimeFormatter

class MovieViewHolder(
    val view: View,
    private val onReservationClick: (MovieUiModel) -> Unit,
) : RecyclerView.ViewHolder(view) {
    private val poster: ImageView = view.findViewById(R.id.poster)
    private val title: TextView = view.findViewById(R.id.movie_title)
    private val screeningDate: TextView = view.findViewById(R.id.screening_date)
    private val runningTime: TextView = view.findViewById(R.id.running_time)
    private val reserveButton: Button = view.findViewById(R.id.reserve_button)

    init {
        reserveButton.setOnClickListener { onReservationClick }
    }

    fun bind(movie: MovieUiModel) {
        val formatter =
            DateTimeFormatter.ofPattern(view.context.getString(R.string.date_format))
        val startDate = movie.startDate.format(formatter)
        val endDate = movie.endDate.format(formatter)

        poster.setImageResource(movie.poster)
        title.text = movie.title
        screeningDate.text =
            view.context.getString(R.string.screening_dates_format, startDate, endDate)
        runningTime.text =
            view.context.getString(R.string.running_type_format, movie.runningTime)
        reserveButton.setOnClickListener { onReservationClick(movie) }
    }
}
