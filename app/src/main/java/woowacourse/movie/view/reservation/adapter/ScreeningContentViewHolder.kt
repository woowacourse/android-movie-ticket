package woowacourse.movie.view.reservation.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import woowacourse.movie.domain.reservation.ScreeningContent

abstract class ScreeningContentViewHolder(
    itemView: View,
) : RecyclerView.ViewHolder(itemView) {
    abstract fun bind(item: ScreeningContent)
}
