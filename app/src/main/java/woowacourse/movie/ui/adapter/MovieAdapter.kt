package woowacourse.movie.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import woowacourse.movie.R
import woowacourse.movie.ui.model.AdModel
import woowacourse.movie.ui.model.MovieModel

class MovieAdapter(
    private val movies: List<MovieModel>,
    private val ads: List<AdModel>,
    private val onMovieItemClick: (MovieModel) -> Unit,
    private val onAdItemClick: (AdModel) -> Unit
) : RecyclerView.Adapter<ViewHolder>() {
    private val onMovieItemViewClick: (Int) -> Unit = { onMovieItemClick(getMovie(it)) }
    private val onAdItemViewClick: (Int) -> Unit = { onAdItemClick(getAd(it)) }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return when (viewType) {
            MovieListViewType.MovieViewType.value -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.movie_item, parent, false)

                MovieViewHolder(view, onMovieItemViewClick)
            }
            MovieListViewType.AdViewType.value -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.advertise_item, parent, false)

                AdViewHolder(view, onAdItemViewClick)
            }
            else -> throw IllegalArgumentException("Wrong View Type")
        }
    }

    override fun getItemCount(): Int = movies.size + ads.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        when (holder) {
            is MovieViewHolder -> holder.bind(getMovie(position))
            is AdViewHolder -> holder.bind(getAd(position))
        }
    }

    override fun getItemViewType(position: Int): Int {
        if (isAdTime(position)) return MovieListViewType.AdViewType.value
        return MovieListViewType.MovieViewType.value
    }

    private fun isAdTime(position: Int) = (position + 1) % 4 == 0

    private fun getMovie(position: Int): MovieModel = movies[position - (position / AD_INTERVAL)]

    private fun getAd(position: Int): AdModel = ads[position / AD_INTERVAL]

    companion object {
        private const val AD_INTERVAL = 4
    }
}
