package woowacourse.movie.view.movies.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import woowacourse.movie.R
import woowacourse.movie.domain.MovieItem
import woowacourse.movie.view.movies.OnMovieEventListener

class MovieAdapter(
    private val eventListener: OnMovieEventListener,
) : ListAdapter<MovieItem, RecyclerView.ViewHolder>(MovieItemDiffCallback) {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): RecyclerView.ViewHolder {
        return if (viewType == TYPE_MOVIE) {
            val view =
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.movie_item, parent, false)
            MovieViewHolder(view, eventListener)
        } else {
            val view =
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.advertisement_item, parent, false)
            AdViewHolder(view)
        }
    }

    override fun onBindViewHolder(
        holder: RecyclerView.ViewHolder,
        position: Int,
    ) {
        when (val item = getItem(position)) {
            is MovieItem.Movie -> {
                if (holder is MovieViewHolder) {
                    holder.bind(item.movie)
                }
            }
            is MovieItem.Advertisement -> {}
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)) {
            is MovieItem.Movie -> TYPE_MOVIE
            is MovieItem.Advertisement -> TYPE_AD
        }
    }

    companion object {
        private const val TYPE_MOVIE = 0
        private const val TYPE_AD = 1

        private val MovieItemDiffCallback =
            object : DiffUtil.ItemCallback<MovieItem>() {
                override fun areItemsTheSame(
                    oldItem: MovieItem,
                    newItem: MovieItem,
                ): Boolean {
                    return oldItem == newItem
                }

                override fun areContentsTheSame(
                    oldItem: MovieItem,
                    newItem: MovieItem,
                ): Boolean {
                    return oldItem == newItem
                }
            }
    }
}
