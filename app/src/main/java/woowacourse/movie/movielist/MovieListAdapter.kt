package woowacourse.movie.movielist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import woowacourse.movie.R

class MovieListAdapter(
    private val onItemClick: ClickListener,
    private val items: List<FeedItem>,
) : RecyclerView.Adapter<ViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): ViewHolder {
        if (viewType == TYPE_MOVIE) {
            val movieView =
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.movie_item, parent, false)
            return MovieViewHolder(movieView, onItemClick)
        } else {
            val adView =
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.ad_item, parent, false)
            return ADViewHolder(adView)
        }
    }

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int,
    ) {
        if (holder is MovieViewHolder) {
            val item = items[position]
            if (item is FeedItem.MovieFeed) {
                val movie = item.movie
                holder.setItem(movie)
            }
        }
    }

    override fun getItemCount(): Int = items.size

    override fun getItemViewType(position: Int): Int {
        return when (items[position]) {
            is FeedItem.MovieFeed -> TYPE_MOVIE
            is FeedItem.ADFeed -> TYPE_EMPTY
        }
    }

    companion object {
        private const val TYPE_MOVIE = 0
        private const val TYPE_EMPTY = 1
    }
}
