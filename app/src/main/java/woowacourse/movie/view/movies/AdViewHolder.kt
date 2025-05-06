package woowacourse.movie.view.movies

import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import woowacourse.movie.R
import woowacourse.movie.domain.model.Advertisement

class AdViewHolder(
    val view: View,
) : RecyclerView.ViewHolder(view) {
    private val ad = view.findViewById<ImageView>(R.id.ad)

    fun bind(item: Advertisement) {
        ad.setImageResource(item.id)
    }
}
