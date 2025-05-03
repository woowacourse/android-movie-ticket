package woowacourse.movie.ui.main.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import woowacourse.movie.ui.model.MovieItem

abstract class BaseViewHolder<T : MovieItem>(itemView: View) : ViewHolder(itemView) {
    abstract fun bind(item: T)
}
