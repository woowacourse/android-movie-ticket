package woowacourse.movie.view.movies

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import woowacourse.movie.R
import woowacourse.movie.model.movie.Movie

class MovieAdapter(
    private val movies: MutableList<Movie>,
    private val movieClickListener: MovieClickListener,
) : RecyclerView.Adapter<MovieViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): MovieViewHolder {
        val view =
            LayoutInflater
                .from(parent.context)
                .inflate(R.layout.item_movie, parent, false)
        return MovieViewHolder(view, parent.context, movieClickListener)
    }

    override fun onBindViewHolder(
        holder: MovieViewHolder,
        position: Int,
    ) {
        val item: Movie = movies[position]
        holder.bind(item)
    }

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getItemCount(): Int = movies.size

    fun updateMovies(newMovies: List<Movie>) {
        movies.clear()
        movies.addAll(newMovies)
        notifyDataSetChanged()
    }
}
