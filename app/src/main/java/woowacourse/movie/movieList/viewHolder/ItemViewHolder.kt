package woowacourse.movie.movieList.viewHolder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import model.ItemViewType

sealed class ItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    abstract fun bind(itemViewType: ItemViewType)
}
