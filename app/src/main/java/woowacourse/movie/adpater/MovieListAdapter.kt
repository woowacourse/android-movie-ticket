package woowacourse.movie.adpater

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import woowacourse.movie.R
import woowacourse.movie.databinding.AdItemBinding
import woowacourse.movie.databinding.MovieItemBinding
import woowacourse.movie.domain.Movie
import woowacourse.movie.helper.CustomClickListenerHelper.setOnSingleClickListener
import woowacourse.movie.helper.LocalDateHelper.toDotFormat

class MovieListAdapter(
    private val value: List<Movie>,
    private val navigateToBook: (Movie) -> Unit,
    private val navigateToAd: () -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerView.ViewHolder {
        return when (viewType) {
            VIEW_TYPE_MOVIE -> {
                val binding =
                    MovieItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                MovieViewHolder(binding)
            }

            else -> {
                val binding =
                    AdItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                AdViewHolder(binding)
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when {
            isAdPosition(position) -> VIEW_TYPE_AD
            else -> VIEW_TYPE_MOVIE
        }
    }

    private fun isAdPosition(position: Int): Boolean {
        return (position + 1) % 4 == 0
    }

    override fun onBindViewHolder(
        holder: RecyclerView.ViewHolder,
        position: Int
    ) {
        if (getItemViewType(position) == VIEW_TYPE_MOVIE) {
            val realPosition = position - (position / 4)
            (holder as MovieViewHolder).bindMovie(value[realPosition])
        } else {
            (holder as AdViewHolder).bindAd()
        }
    }

    override fun getItemCount(): Int {
        val movieCount = value.size
        val adCount = movieCount / 3
        return movieCount + adCount
    }

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

    inner class AdViewHolder(
        private val binding: AdItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bindAd() {
            binding.root.setOnSingleClickListener {
                navigateToAd()
            }
        }
    }

    companion object {
        private const val VIEW_TYPE_MOVIE = 0
        private const val VIEW_TYPE_AD = 1
    }
}

