package woowacourse.movie.movies

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import woowacourse.movie.R

class MoviesAdapter(
    private val movies: List<MovieUiModel>,
    private val onBookingClick: (MovieUiModel) -> Unit,
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): RecyclerView.ViewHolder =
        if (viewType == VIEW_TYPE_MOVIE) {
            val view =
                LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)
            MovieItemViewHolder(view)
        } else {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.item_ad, parent, false)
            AdItemViewHolder(view)
        }

    override fun onBindViewHolder(
        holder: RecyclerView.ViewHolder,
        position: Int,
    ) {
        if (getItemViewType(position) == VIEW_TYPE_MOVIE) {
            val realMoviePosition = position - position / 4
            val movie = movies[realMoviePosition]
            (holder as MovieItemViewHolder).bind(movie, onBookingClick)
        } else {
            (holder as AdItemViewHolder).bind()
        }
    }

    override fun getItemViewType(position: Int): Int =
        if ((position + 1) % 4 == 0) {
            VIEW_TYPE_AD
        } else {
            VIEW_TYPE_MOVIE
        }

    override fun getItemCount(): Int = movies.size + movies.size / 3

    companion object {
        private const val VIEW_TYPE_MOVIE = 0
        private const val VIEW_TYPE_AD = 1
    }
}
