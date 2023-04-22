package woowacourse.movie.movies

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView
import woowacourse.movie.R
import woowacourse.movie.model.DisplayItem

class MoviesAdapter(
    private val moviesInfo: List<DisplayItem.MovieInfo>,
    private val advertisement: DisplayItem.Advertisement,
    private val onItemViewClickListener: OnItemViewClickListener
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private lateinit var layoutInflater: LayoutInflater

    override fun getItemViewType(position: Int): Int {
        return when (position % CYCLE) {
            ADVERTISEMENT_TURN -> ADVERTISEMENT
            else -> MOVIE
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            MOVIE -> MovieItemViewHolder(
                inflateView(R.layout.item_movie, parent)
            )
            ADVERTISEMENT -> AdvertisementViewHolder(
                inflateView(R.layout.item_advertisement, parent)
            )
            else -> throw IllegalArgumentException(
                parent.context.getString(R.string.view_type_error_msg)
            )
        }
    }

    private fun inflateView(
        @LayoutRes resourceId: Int,
        parent: ViewGroup
    ): View {
        if (!::layoutInflater.isInitialized) {
            layoutInflater = LayoutInflater.from(parent.context)
        }

        return layoutInflater.inflate(resourceId, parent, false)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is MovieItemViewHolder -> {
                val moviesPosition = position - position / CYCLE
                holder.bind(
                    moviesInfo[moviesPosition],
                    onItemViewClickListener::onDisplayItemClicked
                )
            }
            is AdvertisementViewHolder -> holder.bind(
                advertisement,
                onItemViewClickListener::onDisplayItemClicked
            )
        }
    }

    override fun getItemCount(): Int = moviesInfo.size

    companion object {
        private const val MOVIE = 1
        private const val ADVERTISEMENT = 2
        private const val ADVERTISEMENT_TURN = 3
        private const val CYCLE = 4
    }
}
