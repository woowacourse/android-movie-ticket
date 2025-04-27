package woowacourse.movie.movie.adapter

import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import woowacourse.movie.R
import woowacourse.movie.movie.MovieListItem

class AdViewHolder(
    itemView: View,
) : RecyclerView.ViewHolder(itemView) {
    private val image: ImageView = itemView.findViewById(R.id.iv_ad)

    fun binding(ad: MovieListItem.AdvertisementItem) {
        image.setImageResource(ad.imageId)
    }
}
