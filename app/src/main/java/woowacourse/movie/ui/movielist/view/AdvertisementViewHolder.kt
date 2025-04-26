package woowacourse.movie.ui.movielist.view

import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import woowacourse.movie.R
import woowacourse.movie.domain.model.Advertisement

class AdvertisementViewHolder(
    itemView: View,
) : RecyclerView.ViewHolder(itemView) {
    val advertisementImage: ImageView = itemView.findViewById(R.id.img_advertisement)

    fun bind(item: Advertisement) {
        advertisementImage.setImageResource(item.image)
    }
}
