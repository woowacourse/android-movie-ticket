package woowacourse.movie.movies

import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import woowacourse.movie.R
import woowacourse.movie.model.Advertisement

class AdvertisementViewHolder(
    itemView: View,
) : RecyclerView.ViewHolder(itemView) {

    private val adImage: ImageView = itemView.findViewById(R.id.advertisement_image_view)

    fun bind(advertisement: Advertisement, onAdvertisementClicked: (advertisement: Advertisement) -> Unit) {
        advertisement.adImageSrc?.let { adImage.setImageResource(it) }
        adImage.setOnClickListener {
            onAdvertisementClicked(advertisement)
        }
    }
}
