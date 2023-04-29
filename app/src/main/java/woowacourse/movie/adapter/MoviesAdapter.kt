package woowacourse.movie.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import woowacourse.movie.item.ItemType
import woowacourse.movie.item.ModelItem
import woowacourse.movie.viewholder.AdvertisingItemViewHolder
import woowacourse.movie.viewholder.ItemViewHolder
import woowacourse.movie.viewholder.MovieItemViewHolder

class MoviesAdapter(
    private val modelItems: MutableList<ModelItem>
) : RecyclerView.Adapter<ItemViewHolder>() {

    override fun getItemCount(): Int = modelItems.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val itemType: ItemType = ItemType.of(viewType)
        val view = LayoutInflater.from(parent.context).inflate(itemType.layoutRes, parent, false)

        return when (itemType) {
            ItemType.MOVIE -> MovieItemViewHolder(view)
            ItemType.ADVERTISING -> AdvertisingItemViewHolder(view)
        }
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = modelItems[position]
        when (holder) {
            is MovieItemViewHolder -> holder.bind(item)
            is AdvertisingItemViewHolder -> holder.bind(item)
        }
    }

    override fun getItemViewType(position: Int): Int = modelItems[position].itemType.ordinal
}
