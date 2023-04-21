package woowacourse.movie.view

import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import woowacourse.movie.R
import woowacourse.movie.databinding.MovieItemBinding
import woowacourse.movie.util.DATE_FORMATTER
import woowacourse.movie.view.model.MovieListModel.MovieUiModel

class MovieItemViewHolder(
    private val binding: MovieItemBinding,
    private val onViewClick: (Int) -> Unit
) : RecyclerView.ViewHolder(binding.root) {
    private val poster: ImageView = binding.moviePoster
    private val title: TextView = binding.movieTitle
    private val screeningStartDate: TextView = binding.movieScreeningDate
    private val runningTime: TextView = binding.movieRunningTime
    private val reserveNow: Button = binding.reserveNowButton

    init {
        reserveNow.setOnClickListener {
            onViewClick(adapterPosition)
        }
    }

    fun bind(movie: MovieUiModel) {
        val context = binding.root.context
        poster.setImageResource(movie.posterResourceId)
        title.text = movie.title
        screeningStartDate.text =
            context.resources.getString(R.string.screening_date_format).format(
                movie.screeningStartDate.format(DATE_FORMATTER),
                movie.screeningEndDate.format(DATE_FORMATTER)
            )
        runningTime.text = context.resources.getString(R.string.running_time_format)
            .format(movie.runningTime)
    }
}
