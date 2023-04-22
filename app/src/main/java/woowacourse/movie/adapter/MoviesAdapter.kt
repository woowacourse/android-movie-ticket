package woowacourse.movie.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import woowacourse.movie.R
import woowacourse.movie.item.AdvertisingItem
import woowacourse.movie.item.ModelItem
import woowacourse.movie.item.MovieItem
import woowacourse.movie.viewholder.AdvertisingItemViewHolder
import woowacourse.movie.viewholder.ItemViewHolder
import woowacourse.movie.viewholder.MovieItemViewHolder

class MoviesAdapter(
    private val modelItems: MutableList<ModelItem>
) : RecyclerView.Adapter<ItemViewHolder>() {

    override fun getItemCount(): Int = modelItems.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val context: Context = parent.context
        val view = LayoutInflater.from(parent.context).inflate(viewType, parent, false)
        return when (viewType) {
            R.layout.item_movie -> MovieItemViewHolder(view)
            R.layout.item_advertising -> AdvertisingItemViewHolder(view)
            else -> throw IllegalArgumentException()
        }
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = modelItems[position]
        when (holder) {
            is MovieItemViewHolder -> {
                holder.bind(item as MovieItem)
            }
            is AdvertisingItemViewHolder -> {
                holder.bind(item as AdvertisingItem)
            }
        }
    }

    override fun getItemViewType(position: Int): Int = when (modelItems[position]) {
        is MovieItem -> R.layout.item_movie
        is AdvertisingItem -> R.layout.item_advertising
        else -> throw IllegalArgumentException()
    }
}
