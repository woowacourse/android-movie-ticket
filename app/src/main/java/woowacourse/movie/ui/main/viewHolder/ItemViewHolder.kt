package woowacourse.movie.ui.main.viewHolder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import woowacourse.movie.ui.main.itemModel.ItemModel

abstract class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    abstract fun bind(itemModel: ItemModel)
}
