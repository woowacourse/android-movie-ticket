package woowacourse.movie.view.movies

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import woowacourse.movie.R

class MovieListAdapter(
    private val items: List<MovieListItem>,
    private val eventListener: OnMovieEventListener,
) : RecyclerView.Adapter<ViewHolder>() {
    override fun getItemViewType(position: Int): Int =
        when (items[position]) {
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
            else -> throw IllegalArgumentException()
        }
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int,
    ) {
        when (val item = items[position]) {
            is MovieListItem.AdItem -> (holder as AdViewHolder).bind(item.ad)
            is MovieListItem.MovieItem ->
                (holder as MovieViewHolder).bind(
                    item.movie,
                    eventListener,
                )
        }
    }
}
