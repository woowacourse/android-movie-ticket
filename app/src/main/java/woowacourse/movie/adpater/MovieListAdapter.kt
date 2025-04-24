package woowacourse.movie.adpater

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import woowacourse.movie.R
import woowacourse.movie.databinding.MovieItemBinding
import woowacourse.movie.domain.Movie
import woowacourse.movie.helper.CustomClickListenerHelper.setOnSingleClickListener
import woowacourse.movie.helper.LocalDateHelper.toDotFormat

class MovieListAdapter(
    private val value: List<Movie>,
    private val navigateToBook: (Movie) -> Unit,
) : BaseAdapter() {
    override fun getCount(): Int {
        return value.size
    }

    override fun getItem(position: Int): Movie {
        return value[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(
        position: Int,
        convertView: View?,
        parent: ViewGroup
    ): View {
        val movie = getItem(position)
        val view: View
        val binding: MovieItemBinding

        if (convertView == null) {
            binding = MovieItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            view = binding.root
            view.tag = binding
        } else {
            view = convertView
            binding = view.tag as MovieItemBinding
        }

        bindMovie(binding, movie)
        binding.root.setOnSingleClickListener { navigateToBook(movie) }

        return view
    }

    private fun bindMovie(binding: MovieItemBinding, movie: Movie) {
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
