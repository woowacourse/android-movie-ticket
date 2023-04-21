package woowacourse.movie.presentation.activities.movielist.adapter.viewholder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import woowacourse.movie.presentation.model.movieitem.ListItem

abstract class BaseViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    abstract fun <T : ListItem> bind(item: T)
}
