package woowacourse.movie.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import woowacourse.movie.R
import woowacourse.movie.ui.main.itemModel.AdbItemModel
import woowacourse.movie.ui.main.itemModel.ItemModel
import woowacourse.movie.ui.main.itemModel.MovieItemModel
import woowacourse.movie.ui.main.viewHolder.AdbViewHolder
import woowacourse.movie.ui.main.viewHolder.ItemViewHolder
import woowacourse.movie.ui.main.viewHolder.MovieViewHolder

class MovieAdapter(
    movie: List<MovieItemModel>,
    adb: List<AdbItemModel>
) : RecyclerView.Adapter<ItemViewHolder>() {

    private val _items: List<ItemModel>
    val items: List<ItemModel>
        get() = _items.toList()

    private val adbSize = adb.size
    private var curAdbIndex: Int = 0
        set(value) {
            if (value >= adbSize || value < 0) {
                field = 0
                return
            }
            field = value
        }

    init {
        _items = if (adb.isEmpty()) {
            movie.toList()
        } else {
            val allowAdbMaxCount: Int = movie.size / 3
            mutableListOf<ItemModel>().apply {
                addAll(movie.toList())
                for (index in 3..(movie.size + allowAdbMaxCount) step 4) {
                    add(index, adb[curAdbIndex++])
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
            R.layout.adb_item_layout -> AdbViewHolder(itemView)
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
