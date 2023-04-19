package woowacourse.movie.view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.example.domain.Movie
import woowacourse.movie.R
import woowacourse.movie.databinding.MovieItemBinding
import woowacourse.movie.util.DATE_FORMATTER

class MovieListAdapter(
    private val movies: List<Movie>,
    private val onReserveListener: OnReserveListener
) : BaseAdapter() {

    override fun getView(position: Int, view: View?, parent: ViewGroup?): View {
        var convertView = view
        val viewHolder: MovieItemViewHolder
        var binding: MovieItemBinding? = null
        if (convertView == null) {
            binding = MovieItemBinding.inflate(LayoutInflater.from(parent?.context), parent, false)
            convertView = binding.root

            viewHolder = MovieItemViewHolder(
                binding.moviePoster,
                binding.movieTitle,
                binding.movieScreeningDate,
                binding.movieRunningTime,
                binding.reserveNowButton
            )
            convertView.tag = viewHolder
        } else {
            viewHolder = view?.tag as MovieItemViewHolder
        }
        val movie = movies[position]
        binding?.let {
            initMovieItemView(binding.root.context, viewHolder, movie)
        }
        return convertView
    }

    private fun initMovieItemView(
        context: Context,
        viewHolder: MovieItemViewHolder,
        movie: Movie
    ) {
        viewHolder.apply {
            poster.setImageResource(movie.posterResourceId)
            title.text = movie.title
            screeningStartDate.text =
                context.resources.getString(R.string.screening_date_format).format(
                    movie.screeningStartDate.format(DATE_FORMATTER),
                    movie.screeningEndDate.format(DATE_FORMATTER)
                )
            runningTime.text = context.resources.getString(R.string.running_time_format)
                .format(movie.runningTime.value)
            reserveNow.setOnClickListener {
                onReserveListener.onClick(movie)
            }
        }
    }

    override fun getCount(): Int {
        return movies.size
    }

    override fun getItem(position: Int): Any {
        return movies[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    interface OnReserveListener {
        fun onClick(movie: Movie)
    }
}
