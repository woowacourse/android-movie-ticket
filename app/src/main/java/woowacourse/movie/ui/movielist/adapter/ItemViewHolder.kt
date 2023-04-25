package woowacourse.movie.ui.movielist.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import woowacourse.movie.model.MovieItem

abstract class ItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    abstract fun bind(item: MovieItem)
}
