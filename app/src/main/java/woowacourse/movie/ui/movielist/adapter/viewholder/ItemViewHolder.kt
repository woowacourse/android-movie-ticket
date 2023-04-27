package woowacourse.movie.ui.movielist.adapter.viewholder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import woowacourse.movie.model.MovieListModel

abstract class ItemViewHolder(
    view: View,
) : RecyclerView.ViewHolder(view) {
    abstract fun bind(model: MovieListModel)
}
