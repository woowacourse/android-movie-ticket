package woowacourse.movie.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import woowacourse.movie.R
import woowacourse.movie.ui.main.itemModel.AdvItemModel
import woowacourse.movie.ui.main.itemModel.ItemModel
import woowacourse.movie.ui.main.itemModel.MovieItemModel
import woowacourse.movie.ui.main.viewHolder.AdvViewHolder
import woowacourse.movie.ui.main.viewHolder.ItemViewHolder
import woowacourse.movie.ui.main.viewHolder.MovieViewHolder

class MainPageAdapter(
    movie: List<MovieItemModel>,
    adv: List<AdvItemModel>
) : RecyclerView.Adapter<ItemViewHolder>() {

    private val _items: List<ItemModel>
    val items: List<ItemModel>
        get() = _items.toList()

    init {
        _items = if (adv.isEmpty()) {
            movie.toList()
        } else {
            var curAdvIndex = 0
            val advSize = adv.size
            val allowAdvMaxCount: Int = movie.size / 3
            mutableListOf<ItemModel>().apply {
                addAll(movie.toList())
                for (index in 3..(movie.size + allowAdvMaxCount) step 4) {
                    add(index, adv[(curAdvIndex++) % advSize])
                }
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ItemViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(viewType, parent, false)

        return when (viewType) {
            R.layout.movie_item_layout -> MovieViewHolder(itemView)
            R.layout.adb_item_layout -> AdvViewHolder(itemView)
            else -> throw IllegalArgumentException()
        }
    }

    override fun onBindViewHolder(
        holder: ItemViewHolder,
        position: Int
    ) {
        holder.bind(_items[position])
    }

    override fun getItemCount(): Int = _items.size

    override fun getItemViewType(position: Int): Int {
        return _items[position].layoutId
    }
}
