package woowacourse.movie.presentation.movieList.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import woowacourse.movie.R
import woowacourse.movie.model.Movie
import woowacourse.movie.presentation.movieList.MovieListClickListener

class MovieAdapter(
    private var movies: List<Movie> = emptyList(),
    private val listener: MovieListClickListener,
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun getItemViewType(position: Int): Int {
        if ((position + 1) % AD_POSITION == 0) return AD_VIEW_TYPE
        return MOVIE_VIEW_TYPE
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            MOVIE_VIEW_TYPE -> {
                val itemView = inflater.inflate(R.layout.item_movie, parent, false)
                MovieViewHolder(itemView, listener)
            }

            else -> {
                val itemView = inflater.inflate(R.layout.item_ad, parent, false)
                AdViewHolder(itemView)
            }
        }
    }

    override fun onBindViewHolder(
        holder: RecyclerView.ViewHolder,
        position: Int,
    ) {
        val item = movies[(position - position / AD_POSITION)]
        when (holder) {
            is MovieViewHolder -> holder.bind(item)
            is AdViewHolder -> holder.bind()
        }
    }

    override fun getItemId(position: Int): Long = movies[position].id.toLong()

    override fun getItemCount(): Int {
        val adCount = if (movies.size >= AD_INTERVAL) movies.size / AD_INTERVAL else 0
        return movies.size + adCount
    }

    companion object {
        private const val MOVIE_VIEW_TYPE = 0
        private const val AD_VIEW_TYPE = 1
        private const val AD_INTERVAL = 3
        private const val AD_POSITION = 4
    }
}
