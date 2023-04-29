package woowacourse.movie.viewholder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import woowacourse.movie.item.ModelItem

abstract class ItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    abstract fun bind(modelItem: ModelItem)

    abstract fun itemClickEvent(model: ModelItem)
}
