package woowacourse.movie.ui.movielistactivity

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import woowacourse.movie.R
import woowacourse.movie.model.MovieDataState
import woowacourse.movie.ui.viewholder.AdvertisementViewHolder
import woowacourse.movie.ui.viewholder.MovieViewHolder

class MovieListAdapter(
    private val movies: List<MovieDataState>,
    private val advertisementImage: Int,
    private val onMovieClickListener: (item: MovieDataState) -> Unit,
    private val onAdClickListener: () -> Unit
) : RecyclerView.Adapter<ViewHolder>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)

        return when (ItemViewType.of(viewType)) {
            ItemViewType.MOVIE -> MovieViewHolder(view.inflate(R.layout.movie_list_item, viewGroup, false))
            ItemViewType.ADVERTISEMENT -> AdvertisementViewHolder(view.inflate(R.layout.advertisement_lsit_item, viewGroup, false))
        }
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        when (viewHolder) {
            is MovieViewHolder -> viewHolder.bind(movies[position - (position / 4)], onMovieClickListener)
            is AdvertisementViewHolder -> viewHolder.bind(advertisementImage, onAdClickListener)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (position % ADVERTISEMENT_CYCLE) {
            ADVERTISEMENT_POSITION -> ItemViewType.ADVERTISEMENT.position
            else -> ItemViewType.MOVIE.position
        }
    }

    override fun getItemCount() = movies.size + (movies.size / 3)

    companion object {
        private const val ADVERTISEMENT_CYCLE = 4
        private const val ADVERTISEMENT_POSITION = 3
    }
}
