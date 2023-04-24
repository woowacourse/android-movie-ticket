package woowacourse.movie.adapter.viewholder

import android.view.View
import android.widget.ImageView
import woowacourse.movie.R
import woowacourse.movie.model.MovieListItem

class AdViewHolder(
    view: View,
    items: List<MovieListItem>,
    onItemClick: (item: MovieListItem) -> Unit
) :
    CustomViewHolder(view) {
    private val adImage: ImageView = view.findViewById(R.id.img_ad)

    init {
        adImage.setOnClickListener { onItemClick(items[adapterPosition]) }
    }

    override fun bind(item: MovieListItem) {
        item as MovieListItem.AdModel
        adImage.setImageResource(item.image)
    }
}
