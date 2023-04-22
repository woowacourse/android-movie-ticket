package woowacourse.movie.ui.movielist.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import woowacourse.movie.R
import woowacourse.movie.model.MovieUI
import kotlin.math.min

class MovieListAdapter(
    private val movies: List<MovieUI>,
    private val onBookClick: (MovieUI) -> Unit
) : RecyclerView.Adapter<MovieListViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieListViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_movie, parent, false)

        return MovieListViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieListViewHolder, position: Int) {
        holder.bind(movies[position], onBookClick)
    }

    override fun getItemCount(): Int = min(movies.size, LIMIT_ITEM_COUNT)

    companion object {
        private const val LIMIT_ITEM_COUNT = 10_000
    }
}
