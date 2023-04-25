package woowacourse.movie.ui.movielist.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import woowacourse.movie.R
import woowacourse.movie.model.MovieUI
import woowacourse.movie.ui.movielist.model.AdvertisementUI
import woowacourse.movie.ui.movielist.model.ItemUI
import woowacourse.movie.ui.movielist.model.OnItemClick
import kotlin.math.min

class MovieListAdapter(
    private val movies: List<MovieUI>,
    private val advertisements: List<AdvertisementUI>,
    private val onItemClick: OnItemClick
) : RecyclerView.Adapter<ItemViewHolder>() {
    private val _items: List<ItemUI> = if (advertisements.isEmpty()) {
        movies.toList()
    } else {
        var curAdIdx = DEFAULT_ADVERTISEMENT_IDX
        val displayAdCount: Int = movies.size / ADVERTISEMENT_POSITION
        mutableListOf<ItemUI>().apply {
            addAll(movies.toList())
            for (index in ADVERTISEMENT_POSITION..(movies.size + displayAdCount) step MOVIE_LIST_CYCLE) {
                add(index, advertisements[(curAdIdx++) % advertisements.size])
            }
        }
    }
    private val onBookClick: (Int) -> Unit = { onItemClick.onBookClick(_items[it] as MovieUI) }
    private val onAdvertisementClick: (Int) -> Unit = { onItemClick.onAdvertisementClick(_items[it] as AdvertisementUI) }
    private lateinit var layoutInflater: LayoutInflater

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        if (!::layoutInflater.isInitialized) {
            layoutInflater = LayoutInflater.from(parent.context)
        }

        return when (viewType) {
            ItemViewType.MOVIE -> {
                val view = layoutInflater.inflate(R.layout.item_movie, parent, false)
                MovieListViewHolder(view, onBookClick)
            }
            ItemViewType.ADVERTISEMENT -> {
                val view = layoutInflater.inflate(R.layout.item_advertisement, parent, false)
                AdvertisementViewHolder(view, onAdvertisementClick)
            }
            else -> throw IllegalArgumentException()
        }
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        when (holder) {
            is MovieListViewHolder -> {
                holder.bind(_items[position] as MovieUI)
            }
            is AdvertisementViewHolder -> {
                holder.bind(_items[position] as AdvertisementUI)
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (position % MOVIE_LIST_CYCLE) {
            ADVERTISEMENT_POSITION -> ItemViewType.ADVERTISEMENT
            else -> ItemViewType.MOVIE
        }
    }

    override fun getItemCount(): Int {
        return min(_items.size, LIMIT_ITEM_COUNT)
    }

    companion object {
        private const val MOVIE_LIST_CYCLE = 4
        private const val ADVERTISEMENT_POSITION = 3
        private const val DEFAULT_ADVERTISEMENT_IDX = 0
        private const val LIMIT_ITEM_COUNT = 10_000
    }
}
