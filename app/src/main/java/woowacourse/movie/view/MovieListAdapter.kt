package woowacourse.movie.view

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.Movie
import woowacourse.movie.R
import woowacourse.movie.databinding.MovieItemBinding
import woowacourse.movie.util.DATE_FORMATTER

class MovieListAdapter(
    private val movies: List<Movie>,
    private val onReserveListener: OnReserveListener
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    interface OnReserveListener {
        fun onClick(movie: Movie)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.movie_item, parent, false)
        return MovieItemViewHolder(MovieItemBinding.bind(view))
    }

    override fun getItemCount(): Int = movies.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val movie = movies[position]
        when (holder) {
            is MovieItemViewHolder -> {
                bindMovieItemViewHolder(holder.binding.root.context, holder, movie)
            }
        }
    }

    private fun bindMovieItemViewHolder(
        context: Context,
        viewHolder: MovieItemViewHolder,
        movie: Movie
    ): MovieItemViewHolder {
        return viewHolder.apply {
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

    companion object {

    }
}
