package woowacourse.movie.ui.movielist.adapter

import android.view.View
import android.widget.ImageView
import woowacourse.movie.R
import woowacourse.movie.model.MovieItem

class AdvertisementViewHolder(view: View, onAdvertisementClick: (Int) -> Unit) :
    ItemViewHolder(view) {
    private val advertisement = view.findViewById<ImageView>(R.id.iv_advertisement)

    init {
        advertisement.setOnClickListener { onAdvertisementClick(adapterPosition) }
    }

    override fun bind(item: MovieItem) {
        (item as MovieItem.AdvertisementUI).image?.let { advertisement.setImageResource(it) }
    }
}
