package woowacourse.movie.view

import androidx.recyclerview.widget.RecyclerView
import woowacourse.movie.R
import woowacourse.movie.databinding.MovieItemBinding
import woowacourse.movie.util.DATE_FORMATTER
import woowacourse.movie.view.model.MovieListModel.MovieUiModel

class MovieItemViewHolder(
    private val binding: MovieItemBinding,
    private val onViewClick: (Int) -> Unit
) : RecyclerView.ViewHolder(binding.root) {
    init {
        binding.reserveNowButton.setOnClickListener {
            onViewClick(adapterPosition)
        }
    }

    fun bind(movie: MovieUiModel) {
        binding.apply {
            val context = binding.root.context
            moviePoster.setImageResource(movie.posterResourceId)
            movieTitle.text = movie.title
            movieScreeningDate.text =
                context.resources.getString(R.string.screening_date_format).format(
                    movie.screeningStartDate.format(DATE_FORMATTER),
                    movie.screeningEndDate.format(DATE_FORMATTER)
                )
            movieRunningTime.text = context.resources.getString(R.string.running_time_format)
                .format(movie.runningTime)
        }
    }
}
