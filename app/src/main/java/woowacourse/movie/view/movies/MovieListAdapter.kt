package woowacourse.movie.view.movies

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import woowacourse.movie.R

class MovieListAdapter(
    private val eventListener: OnMovieEventListener,
) : ListAdapter<MovieListItem, ViewHolder>(MovieListItemDiffCallback()) {
    override fun getItemViewType(position: Int): Int =
        when (getItem(position)) {
            is MovieListItem.AdItem -> R.layout.item_advertisement
            is MovieListItem.MovieItem -> R.layout.item_movie
        }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): ViewHolder {
        val view: View
        return when (viewType) {
            R.layout.item_movie -> {
                view = LayoutInflater.from(parent.context).inflate(viewType, parent, false)
                MovieViewHolder(view)
            }

            R.layout.item_advertisement -> {
                view = LayoutInflater.from(parent.context).inflate(viewType, parent, false)
                AdViewHolder(view)
            }
            else -> throw IllegalArgumentException("알 수 없는 화면입니다: $viewType")
        }
    }

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int,
    ) {
        when (val item = getItem(position)) {
            is MovieListItem.AdItem -> (holder as AdViewHolder).bind(item.ad)
            is MovieListItem.MovieItem ->
                (holder as MovieViewHolder).bind(
                    item.movie,
                    eventListener,
                )
        }
    }

    fun updateItems(newList: List<MovieListItem>) {
        submitList(newList)
    }
}
