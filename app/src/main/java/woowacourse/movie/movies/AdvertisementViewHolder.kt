package woowacourse.movie.movies

import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import woowacourse.movie.R
import woowacourse.movie.model.MoviesRecyclerItem

class AdvertisementViewHolder(
    itemView: View,
) : RecyclerView.ViewHolder(itemView) {

    private val adImage: ImageView = itemView.findViewById(R.id.advertisement_image_view)

    fun bind(
        advertisement: MoviesRecyclerItem.Advertisement,
        onAdvertisementClicked: (advertisement: MoviesRecyclerItem.Advertisement) -> Unit
    ) {
        adImage.setImageResource(advertisement.adImageSrc)
        adImage.setOnClickListener {
            onAdvertisementClicked(advertisement)
        }
    }
}
