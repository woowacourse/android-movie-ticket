package woowacourse.movie.movies

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import woowacourse.movie.R
import woowacourse.movie.model.MovieInfo

class MoviesAdapter(
    private val moviesInfo: List<MovieInfo>,
    private val onItemClicked: (movieInfo: MovieInfo) -> Unit
) : RecyclerView.Adapter<MovieItemViewHolder>() {

    private lateinit var layoutInflater: LayoutInflater

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieItemViewHolder {
        if (!::layoutInflater.isInitialized) {
            layoutInflater = LayoutInflater.from(parent.context)
        }
        val view = layoutInflater.inflate(R.layout.item_movie, parent, false)

        return MovieItemViewHolder(view)
    }

    override fun getItemCount(): Int = moviesInfo.size

    override fun onBindViewHolder(holder: MovieItemViewHolder, position: Int) {
        holder.bind(moviesInfo[position], onItemClicked)
    }
}
