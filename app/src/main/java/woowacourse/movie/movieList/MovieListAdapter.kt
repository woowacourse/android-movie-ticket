package woowacourse.movie.movieList

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import model.ItemViewType
import woowacourse.movie.R
import woowacourse.movie.movieList.viewHolder.AdViewHolder
import woowacourse.movie.movieList.viewHolder.ItemViewHolder
import woowacourse.movie.movieList.viewHolder.MovieViewHolder

class MovieListAdapter(
    private val items: List<ItemViewType>,
    private val onClickButton: (ItemViewType) -> Unit,
    private val onAdClick: (ItemViewType) -> Unit,
) : RecyclerView.Adapter<ItemViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)

        return when (viewType) {
            ItemViewType.Movie.type -> {
                MovieViewHolder(
                    layoutInflater.inflate(R.layout.item_movie_movie_list, parent, false),
                ) { position -> onClickButton(items[position]) }
            }
            ItemViewType.Ad.type -> {
                AdViewHolder(
                    layoutInflater.inflate(R.layout.item_ad_movie_list, parent, false),
                ) { position -> onAdClick(items[position]) }
            }
            else -> throw IllegalArgumentException(ERROR_INVALID_VIEW_TYPE)
        }
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        when (items[position]) {
            is ItemViewType.Movie -> holder.bind(items[position])
            is ItemViewType.Ad -> holder.bind(items[position])
        }
    }

    override fun getItemCount(): Int = items.size

    override fun getItemViewType(position: Int): Int = when (items[position]) {
        is ItemViewType.Movie -> ItemViewType.Movie.type
        is ItemViewType.Ad -> ItemViewType.Ad.type
    }

    companion object {
        private const val ERROR_INVALID_VIEW_TYPE = "해당 뷰 타입은 존재하지 않습니다."
    }
}
