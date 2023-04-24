package woowacourse.movie.movies

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import woowacourse.movie.R
import woowacourse.movie.model.MovieRecyclerItem
import woowacourse.movie.model.MovieRecyclerItemViewType

class MoviesAdapter(
    private val moviesRecyclerItems: List<MovieRecyclerItem>,
    private val onItemViewClickListener: OnItemViewClickListener
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun getItemViewType(position: Int): Int {
        return MovieRecyclerItemViewType.valueOf(position).ordinal
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater: LayoutInflater = LayoutInflater.from(parent.context)

        return when (viewType) {
            MovieRecyclerItemViewType.MOVIE.ordinal -> MovieItemViewHolder(
                layoutInflater.inflate(R.layout.item_movie, parent, false)
            )
            MovieRecyclerItemViewType.ADVERTISEMENT.ordinal -> AdvertisementViewHolder(
                layoutInflater.inflate(R.layout.item_advertisement, parent, false)
            )
            else -> throw IllegalArgumentException(
                parent.context.getString(R.string.view_type_error_msg)
            )
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is MovieItemViewHolder -> {
                holder.bind(
                    moviesRecyclerItems[position] as MovieRecyclerItem.MovieInfo,
                    onItemViewClickListener::onDisplayItemClicked
                )
            }
            is AdvertisementViewHolder -> holder.bind(
                moviesRecyclerItems[position] as MovieRecyclerItem.Advertisement,
                onItemViewClickListener::onDisplayItemClicked
            )
        }
    }

    override fun getItemCount(): Int = moviesRecyclerItems.size
}
