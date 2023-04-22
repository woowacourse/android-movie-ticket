package woowacourse.movie.adapter.viewholder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import woowacourse.movie.listener.ItemClickListener
import woowacourse.movie.model.MovieListItem

abstract class CustomViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    abstract fun bind(item: MovieListItem, clickListener: ItemClickListener)
}
