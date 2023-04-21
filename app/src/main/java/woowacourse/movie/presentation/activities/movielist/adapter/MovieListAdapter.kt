package woowacourse.movie.presentation.activities.movielist.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView
import woowacourse.movie.R
import woowacourse.movie.presentation.activities.movielist.adapter.type.MovieViewType
import woowacourse.movie.presentation.activities.movielist.adapter.viewholder.BaseViewHolder
import woowacourse.movie.presentation.activities.movielist.adapter.viewholder.MovieViewHolder
import woowacourse.movie.presentation.activities.movielist.adapter.viewholder.NativeAdViewHolder
import woowacourse.movie.presentation.model.movieitem.ListItem
import woowacourse.movie.presentation.model.movieitem.Movie
import java.util.LinkedList

class MovieListAdapter(
    private val adInterval: Int = DEFAULT_AD_INTERVAL,
    private val adTypes: List<ListItem>,
    private val onItemClick: (ListItem) -> Unit = {},
) : RecyclerView.Adapter<BaseViewHolder>() {
    private val items: LinkedList<ListItem> = LinkedList()
    private val onItemViewClick: (Int) -> Unit = { position -> onItemClick(items[position]) }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        when (viewType) {
            MovieViewType.MOVIE -> {
                val movieView = inflate(parent.context, parent, R.layout.item_movie)
                return MovieViewHolder(movieView, onItemViewClick)
            }

            MovieViewType.AD -> {
                val adView = inflate(parent.context, parent, R.layout.item_native_ad)
                return NativeAdViewHolder(adView, onItemViewClick)
            }
        }
        throw IllegalArgumentException(INVALID_VIEW_TYPE_ERROR_MESSAGE)
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemViewType(position: Int): Int = when {
        items[position].isAd() -> MovieViewType.AD
        !items[position].isAd() -> MovieViewType.MOVIE
        else -> throw IllegalArgumentException(INVALID_POSITION_ERROR_MESSAGE)
    }

    override fun getItemCount(): Int = items.size

    fun appendAll(newMovies: List<Movie>) {
        var newAdSize = 0
        newMovies.forEach { newMovie ->
            newAdSize += appendAd()
            items.add(newMovie)
        }
        notifyItemRangeChanged(items.size, newMovies.size + newAdSize)
    }

    private fun appendAd(): Int {
        if ((items.size + 1) % (adInterval + 1) == 0) {
            items.add(adTypes.random())
            return 1
        }
        return 0
    }

    private fun inflate(context: Context, parent: ViewGroup, @LayoutRes resId: Int): View =
        LayoutInflater.from(context).inflate(resId, parent, false)

    companion object {
        private const val DEFAULT_AD_INTERVAL = 3

        private const val INVALID_VIEW_TYPE_ERROR_MESSAGE = "올바르지 않은 뷰 유형입니다."
        private const val INVALID_POSITION_ERROR_MESSAGE = "올바르지 않은 순서입니다."
    }
}
