package woowacourse.movie.ui.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import woowacourse.movie.R
import woowacourse.movie.domain.model.movie.Movie

class MoviesAdapter(
    private val movies: List<Movie>,
    private val advertisements: List<Int>,
    private val onClick: (Movie) -> Unit,
) : RecyclerView.Adapter<MoviesViewHolder>() {
    override fun getItemViewType(position: Int): Int {
        return if ((position + 1) % 4 == 0) ADVERTISEMENT_TYPE else MOVIE_TYPE
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): MoviesViewHolder {
        return when (viewType) {
            MOVIE_TYPE -> {
                val view =
                    LayoutInflater.from(parent.context).inflate(R.layout.movie_item, parent, false)
                MoviesViewHolder.MovieViewHolder(view, onClick)
            }

            else -> {
                val view =
                    LayoutInflater.from(parent.context)
                        .inflate(R.layout.advertisement_item, parent, false)
                MoviesViewHolder.AdvertisementViewHolder(view)
            }
        }
    }

    override fun getItemCount(): Int {
        return movies.size + movies.size / 3
    }

    override fun onBindViewHolder(
        holder: MoviesViewHolder,
        position: Int,
    ) {
        when (holder) {
            is MoviesViewHolder.AdvertisementViewHolder -> {
                val adIndex = position / 4
                holder.bind(advertisements[adIndex % advertisements.size])
            }

            is MoviesViewHolder.MovieViewHolder -> {
                val movieIndex = position - position / 4
                holder.bind(movies[movieIndex])
            }
        }
    }

    companion object {
        private const val MOVIE_TYPE = 0
        private const val ADVERTISEMENT_TYPE = 1
    }
}
