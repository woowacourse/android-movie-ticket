package woowacourse.movie.feature.movieSelection.adapter

import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import woowacourse.movie.R
import woowacourse.movie.feature.model.movie.MovieListItem.MovieUiModel
import woowacourse.movie.util.Formatter

class MovieViewHolder(
    val view: View,
    private val onReservationClick: (MovieUiModel) -> Unit,
) : RecyclerView.ViewHolder(view) {
    private var movie: MovieUiModel? = null
    private val poster: ImageView = view.findViewById(R.id.poster)
    private val title: TextView = view.findViewById(R.id.movie_title)
    private val screeningDate: TextView = view.findViewById(R.id.screening_date)
    private val runningTime: TextView = view.findViewById(R.id.running_time)
    private val reserveButton: Button = view.findViewById(R.id.reserve_button)

    init {
        reserveButton.setOnClickListener {
            movie?.let { movie -> onReservationClick(movie) }
        }
    }

    fun bind(movie: MovieUiModel) {
        this.movie = movie
        poster.setImageResource(movie.poster)
        title.text = movie.title
        screeningDate.text =
            view.context.getString(
                R.string.template_screening_dates,
                Formatter.format(movie.startDate),
                Formatter.format(movie.endDate),
            )
        runningTime.text =
            view.context.getString(
                R.string.template_running_type,
                movie.runningTime,
            )
    }
}
