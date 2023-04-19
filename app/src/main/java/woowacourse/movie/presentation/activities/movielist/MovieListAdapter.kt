package woowacourse.movie.presentation.activities.movielist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import woowacourse.movie.R
import woowacourse.movie.databinding.ItemMovieBinding
import woowacourse.movie.presentation.model.Movie

class MovieListAdapter(
    private val movies: List<Movie>,
    private val onBookBtnClick: (Movie) -> Unit,
) : RecyclerView.Adapter<MovieListAdapter.MovieViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val movieView = ItemMovieBinding.inflate(layoutInflater, parent, false)
        return MovieViewHolder(movieView, onBookBtnClick)
    }

    override fun getItemCount(): Int = movies.size

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(movies[position])
    }

    class MovieViewHolder(
        private val binding: ItemMovieBinding,
        private val onBookBtnClick: (Movie) -> Unit = {},
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Movie) {
            val context = binding.root.context
            with(item) {
                binding.ivPoster.setImageResource(thumbnail)
                binding.tvTitle.text = title
                binding.tvDate.text = context.getString(
                    R.string.movie_release_date,
                    startDate.formattedDate,
                    endDate.formattedDate
                )
                binding.tvRunningTime.text =
                    context.getString(R.string.movie_running_time, runningTime)
                binding.btnBook.setOnClickListener { onBookBtnClick(item) }
            }
        }
    }
}
