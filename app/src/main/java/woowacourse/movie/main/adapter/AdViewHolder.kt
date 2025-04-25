package woowacourse.movie.main.adapter

import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import woowacourse.movie.R
import woowacourse.movie.main.Item

class AdViewHolder(
    itemView: View,
) : RecyclerView.ViewHolder(itemView) {
    private val image: ImageView = itemView.findViewById(R.id.iv_ad)

    fun binding(ad: Item.AdvertisementItem) {
        image.setImageResource(ad.imageId)
    }
}
