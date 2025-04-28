package woowacourse.movie.view.movies.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import woowacourse.movie.R
import woowacourse.movie.domain.Movie
import woowacourse.movie.view.movies.OnMovieEventListener

class MovieAdapter(
    private val items: List<Movie>,
    private val eventListener: OnMovieEventListener,
) : RecyclerView.Adapter<MovieViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): MovieViewHolder {
        val view =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.movie_item, parent, false)
        return MovieViewHolder(view, eventListener)
    }

    override fun onBindViewHolder(
        holder: MovieViewHolder,
        position: Int,
    ) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size
}
