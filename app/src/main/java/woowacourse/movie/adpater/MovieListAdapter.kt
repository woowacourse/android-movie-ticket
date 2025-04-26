package woowacourse.movie.adpater

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import woowacourse.movie.R
import woowacourse.movie.databinding.MovieItemBinding
import woowacourse.movie.domain.Movie
import woowacourse.movie.helper.CustomClickListenerHelper.setOnSingleClickListener
import woowacourse.movie.helper.LocalDateHelper.toDotFormat

class MovieListAdapter(
    private val value: List<Movie>,
    private val navigateToBook: (Movie) -> Unit,
) : RecyclerView.Adapter<MovieListAdapter.MovieViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MovieViewHolder {
        val binding = MovieItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: MovieViewHolder,
        position: Int
    ) {
        holder.bindMovie(value[position])
    }

    override fun getItemCount(): Int = value.size

    inner class MovieViewHolder(
        private val binding: MovieItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bindMovie(movie: Movie) {
            binding.root.setOnSingleClickListener { navigateToBook(movie) }

            binding.movieTitle.text = movie.title
            binding.moviePoster.setImageResource(movie.poster)
            binding.movieDate.text = binding.movieDate.context.getString(
                R.string.movie_screening_date,
                movie.screeningPeriod.screeningStartDate.toDotFormat(),
                movie.screeningPeriod.screeningEndDate.toDotFormat()
            )
            binding.movieRunningTime.text = binding.movieRunningTime.context.getString(
                R.string.movie_running_time,
                movie.runningTime
            )
            binding.movieBookBtn.commonButton.text =
                binding.movieBookBtn.commonButton.context.getString(R.string.movie_book)
            binding.movieBookBtn.commonButton.setOnSingleClickListener { navigateToBook(movie) }
        }
    }
}
