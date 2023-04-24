package woowacourse.movie.movies

import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import woowacourse.movie.R
import woowacourse.movie.model.MovieRecyclerItem

class AdvertisementViewHolder(
    itemView: View,
) : RecyclerView.ViewHolder(itemView) {

    private val adImage: ImageView = itemView.findViewById(R.id.advertisement_image_view)

    fun bind(
        advertisement: MovieRecyclerItem.Advertisement,
        onAdvertisementClicked: (advertisement: MovieRecyclerItem.Advertisement) -> Unit
    ) {
        adImage.setImageResource(advertisement.adImageSrc)
        adImage.setOnClickListener {
            onAdvertisementClicked(advertisement)
        }
    }
}
