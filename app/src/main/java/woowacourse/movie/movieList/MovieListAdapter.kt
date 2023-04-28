package woowacourse.movie.movieList

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import model.ItemViewType
import woowacourse.movie.R
import woowacourse.movie.movieList.viewHolder.AdViewHolder
import woowacourse.movie.movieList.viewHolder.MovieViewHolder

class MovieListAdapter(
    private val items: List<ItemViewType>,
    private val onClickButton: (ItemViewType.MOVIE) -> Unit,
    private val onAdClick: (ItemViewType.AD) -> Unit,
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)

        return when (viewType) {
            ItemViewType.TYPE_MOVIE -> {
                MovieViewHolder(
                    layoutInflater.inflate(R.layout.item_movie_movie_list, parent, false),
                )
            }
            ItemViewType.TYPE_AD -> {
                AdViewHolder(
                    layoutInflater.inflate(R.layout.item_ad_movie_list, parent, false),
                )
            }
            else -> throw IllegalArgumentException(ERROR_INVALID_VIEW_TYPE)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is MovieViewHolder -> holder.bind(items[position] as ItemViewType.MOVIE, onClickButton)
            is AdViewHolder -> holder.bind(items[position] as ItemViewType.AD, onAdClick)
        }
    }

    override fun getItemCount(): Int = items.size

    override fun getItemViewType(position: Int): Int = items[position].type

    companion object {
        private const val ERROR_INVALID_VIEW_TYPE = "해당 뷰 타입은 존재하지 않습니다."
    }
}
