package woowacourse.movie.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import woowacourse.movie.R
import woowacourse.movie.adapter.viewholder.AdViewHolder
import woowacourse.movie.adapter.viewholder.CustomViewHolder
import woowacourse.movie.adapter.viewholder.MovieViewHolder
import woowacourse.movie.model.MovieListItem

class MovieListAdapter(private val items: List<MovieListItem>) :
    RecyclerView.Adapter<CustomViewHolder>() {

    override fun getItemViewType(position: Int): Int {
        return when (items[position]) {
            is MovieListItem.MovieModel -> MOVIE_ITEM_VIEW_TYPE
            is MovieListItem.AdModel -> AD_ITEM_VIEW_TYPE
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        return when (viewType) {
            MOVIE_ITEM_VIEW_TYPE -> {
                val view =
                    LayoutInflater.from(parent.context).inflate(R.layout.movie_item, parent, false)
                MovieViewHolder(view)
            }
            AD_ITEM_VIEW_TYPE -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.ad_item, parent, false)
                AdViewHolder(view)
            }
            else -> throw IllegalArgumentException(VIEW_TYPE_ERROR)
        }
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size

    companion object {
        private const val MOVIE_ITEM_VIEW_TYPE = 0
        private const val AD_ITEM_VIEW_TYPE = 1
        private const val VIEW_TYPE_ERROR = "알 수 없는 뷰타입"
    }
}
