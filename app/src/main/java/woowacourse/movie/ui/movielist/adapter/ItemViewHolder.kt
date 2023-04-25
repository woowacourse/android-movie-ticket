package woowacourse.movie.ui.movielist.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import woowacourse.movie.ui.movielist.model.ItemUI

abstract class ItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    abstract fun bind(item: ItemUI)
}
