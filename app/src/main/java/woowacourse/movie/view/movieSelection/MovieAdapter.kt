package woowacourse.movie.view.movieSelection

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import woowacourse.movie.R
import woowacourse.movie.view.model.movie.MovieListItem
import woowacourse.movie.view.model.movie.MovieListItem.Ad
import woowacourse.movie.view.model.movie.MovieListItem.MovieUiModel

class MovieAdapter(
    private val onReservationClick: (MovieUiModel) -> Unit,
) : ListAdapter<MovieListItem, RecyclerView.ViewHolder>(
        object : DiffUtil.ItemCallback<MovieListItem>() {
            override fun areItemsTheSame(
                oldItem: MovieListItem,
                newItem: MovieListItem,
            ): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(
                oldItem: MovieListItem,
                newItem: MovieListItem,
            ): Boolean {
                return oldItem == newItem
            }
        },
    ) {
    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)) {
            is MovieUiModel -> VIEW_TYPE_MOVIE
            is Ad -> VIEW_TYPE_AD
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(viewType, parent, false)

        return when (viewType) {
            VIEW_TYPE_MOVIE -> MovieViewHolder(view, onReservationClick)
            VIEW_TYPE_AD -> AdViewHolder(view)
            else -> throw IllegalStateException()
        }
    }

    override fun onBindViewHolder(
        holder: RecyclerView.ViewHolder,
        position: Int,
    ) {
        when (holder) {
            is MovieViewHolder -> holder.bind(getItem(position) as MovieUiModel)
            is AdViewHolder -> holder.bind()
        }
    }

    companion object {
        private val VIEW_TYPE_MOVIE = R.layout.item_movie
        private val VIEW_TYPE_AD = R.layout.item_ad
    }
}
