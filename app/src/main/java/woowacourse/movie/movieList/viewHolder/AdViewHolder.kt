package woowacourse.movie.movieList.viewHolder

import android.view.View
import android.widget.ImageView
import model.ItemViewType
import woowacourse.movie.R

class AdViewHolder(
    val view: View,
    onAdClick: (Int) -> Unit,
) : ItemViewHolder(view) {
    private val imageView: ImageView = view.findViewById(R.id.ad_banner)

    init {
        imageView.setOnClickListener { onAdClick(adapterPosition) }
    }
    override fun bind(itemViewType: ItemViewType) {
        val movieAdItem = itemViewType as? ItemViewType.Ad ?: return
        imageView.setImageResource(movieAdItem.image)
    }
}
