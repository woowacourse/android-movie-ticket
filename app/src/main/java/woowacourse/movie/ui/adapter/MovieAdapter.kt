package woowacourse.movie.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import woowacourse.movie.R
import woowacourse.movie.ui.model.MovieModel

class MovieAdapter(
    private val movies: List<MovieModel>,
    private val onItemClick: (MovieModel) -> Unit,
) : RecyclerView.Adapter<ViewHolder>() {
    private val onItemViewClick: (Int) -> Unit = { onItemClick(movies[it]) }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.movie_item, parent, false)

        return MovieViewHolder(view, onItemViewClick)
    }

    override fun getItemCount(): Int = movies.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        when (holder) {
            is MovieViewHolder -> holder.bind(movies[position])
        }
    }
}
