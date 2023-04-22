package woowacourse.movie.ui.movielist.adapter

import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import woowacourse.movie.R
import woowacourse.movie.model.AdvertisementUI

class AdvertisementViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val advertisement = view.findViewById<ImageView>(R.id.iv_advertisement)

    fun bind(item: AdvertisementUI, onItemClick: (AdvertisementUI) -> Unit) {
        item.image?.let { advertisement.setImageResource(it) }
        advertisement.setOnClickListener { onItemClick(item) }
    }
}
