package woowacourse.movie.viewholder

import android.view.View
import android.widget.ImageView
import woowacourse.movie.R
import woowacourse.movie.item.ModelItem

class AdvertisingItemViewHolder(
    private val view: View
) : ItemViewHolder(view) {

    private val advertisingImageView: ImageView = view.findViewById(R.id.advertising_image_view)

    override fun bind(modelItem: ModelItem) {
        advertisingImageView.setImageResource(R.drawable.image_advertising)
    }

    override fun itemClickEvent(model: ModelItem) { }
}
