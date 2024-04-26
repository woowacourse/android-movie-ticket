package woowacourse.movie.presentation.screen.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import woowacourse.movie.R
import woowacourse.movie.domain.model.Movie

class MovieScreenAdapter(
    private val context: Context,
    private val ad: String,
    private var movies: List<Movie> = listOf(),
    private val onMovieSelected: (Int) -> Unit,
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            AD_VIEW_TYPE -> {
                val view = LayoutInflater.from(context).inflate(R.layout.ad_item, parent, false)
                AdViewHolder(
                    view = view,
                    context = context,
                )
            }

            else -> {
                val view = LayoutInflater.from(context).inflate(R.layout.movie_item, parent, false)
                MovieViewHolder(
                    view = view,
                    context = context,
                    onMovieSelected = onMovieSelected
                )
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder.itemViewType) {
            AD_VIEW_TYPE -> {
                val adHolder = holder as AdViewHolder
                adHolder.bind(ad)
            }

            MOVIE_VIEW_TYPE -> {
                val movieHolder = holder as MovieViewHolder
                val moviePosition = position - position / (AD_INTERVAL + 1)
                val movie = movies[moviePosition]
                movieHolder.bind(movie)
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if ((position + 1) % (AD_INTERVAL + 1) == 0 && position != 0) {
            AD_VIEW_TYPE
        } else {
            MOVIE_VIEW_TYPE
        }
    }

    override fun getItemCount(): Int {
        val adCount = if (movies.size >= AD_INTERVAL) movies.size / AD_INTERVAL else 0
        return movies.size + adCount
    }

    fun updateMovies(newMovies: List<Movie>) {
        movies = newMovies
        notifyItemInserted(movies.size - 1)
    }

    companion object {
        private const val MOVIE_VIEW_TYPE = 0
        private const val AD_VIEW_TYPE = 1
        private const val AD_INTERVAL = 3
    }
}
