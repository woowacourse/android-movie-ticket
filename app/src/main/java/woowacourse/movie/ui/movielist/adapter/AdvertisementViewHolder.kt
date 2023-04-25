package woowacourse.movie.ui.movielist.adapter

import android.view.View
import android.widget.ImageView
import woowacourse.movie.R
import woowacourse.movie.ui.movielist.model.AdvertisementUI
import woowacourse.movie.ui.movielist.model.ItemUI

class AdvertisementViewHolder(view: View, onAdvertisementClick: (Int) -> Unit) : ItemViewHolder(view) {
    private val advertisement = view.findViewById<ImageView>(R.id.iv_advertisement)

    init {
        advertisement.setOnClickListener { onAdvertisementClick(adapterPosition) }
    }

    override fun bind(item: ItemUI) {
        (item as AdvertisementUI).image?.let { advertisement.setImageResource(it) }
    }
}
